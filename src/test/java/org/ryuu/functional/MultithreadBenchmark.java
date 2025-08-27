package org.ryuu.functional;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 混合工作负载：多线程同时执行 invoke / 少量 add / 少量 remove。
 * 使用 JMH @Group 将不同操作角色放入同一竞争组，真实并发交织。
 */
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MultithreadBenchmark {
    private static final Action NO_OP = () -> {
    };

    /**
     * 每个 group 共用的状态（隔离不同 fork / iteration），避免跨组污染。
     */
    @State(Scope.Group)
    public static class GroupState {
        @Param({"32"}) // 初始委托数量，可调大以放大 invoke 成本与调度差异
        public int baseActions;

        final Actions actions = Actions.event();
        final ConcurrentLinkedQueue<Action> addedQueue = new ConcurrentLinkedQueue<>();
        final AtomicInteger counter = new AtomicInteger();

        @Setup
        public void setup() {
            // 预置 baseActions 个 NO_OP，模拟已有订阅规模
            for (int i = 0; i < baseActions; i++) {
                actions.add(NO_OP);
            }
        }
    }

    /**
     * 主体调用角色：多数线程仅分发。
     */
    @Group("mix")
    @GroupThreads(12) // 可根据总线程数调整比例
    @Benchmark
    public void invoke(GroupState s) {
        s.actions.invoke();
    }

    /**
     * 少量新增：生成独立 Action（匿名类保证对象唯一），入队以便后续 remove。
     * 控制队列上限，避免无限增长影响结果；如果满则直接返回（跳过本次 add）。
     */
    @Group("mix")
    @GroupThreads(2)
    @Benchmark
    public void add(GroupState s) {
        if (s.addedQueue.size() >= 1024) { // 上限控制（可参数化）
            return;
        }
        Action a = new Action() { // 匿名类 -> 唯一实例，避免重复引用影响 remove 语义
            @Override
            public void invoke() {
                s.counter.incrementAndGet(); // 轻微副作用防止被裁剪
            }
        };
        s.actions.add(a);
        s.addedQueue.offer(a);
    }

    /**
     * 少量移除：从队列获取一个已添加的 Action 移除；若暂时没有则跳过。
     */
    @Group("mix")
    @GroupThreads(2)
    @Benchmark
    public void remove(GroupState s) {
        Action a = s.addedQueue.poll();
        if (a != null) {
            s.actions.remove(a);
        }
    }

    /**
     * 方便直接运行：指定总线程数 = 12 + 2 + 2 = 16，与 GroupThreads 匹配。
     */
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(MultithreadBenchmark.class.getName())
                .warmupIterations(8)
                .measurementIterations(8)
                .forks(8)
                .threads(16) // 必须 >= 所有 group 内线程总和；设为恰好 16
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();
        new Runner(opt).run();
    }
}

