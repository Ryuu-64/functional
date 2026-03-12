package org.ryuu.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MulticastDelegate Actions")
class MulticastDelegateTest {

    @Nested
    @DisplayName("添加操作")
    class AddTests {
        @Test
        @DisplayName("应按添加顺序执行所有委托")
        void shouldExecuteAllDelegatesInOrder() {
            StringBuilder stringBuilder = new StringBuilder();
            Actions actions = Actions.delegate();
            actions.add(() -> stringBuilder.append(0));
            actions.add(() -> stringBuilder.append(1));
            actions.add(() -> stringBuilder.append(2));

            actions.invoke();

            assertThat(stringBuilder.toString()).isEqualTo("012");
        }

        @Test
        @DisplayName("应支持添加另一个 MulticastDelegate")
        void shouldSupportAddingAnotherMulticastDelegate() {
            StringBuilder stringBuilder = new StringBuilder();
            Actions actions1 = Actions.delegate();
            actions1.add(() -> stringBuilder.append(0));
            actions1.add(() -> stringBuilder.append(1));
            actions1.add(() -> stringBuilder.append(2));

            Actions actions2 = Actions.delegate();
            actions2.add(() -> stringBuilder.append(0));
            actions2.add(() -> stringBuilder.append(1));
            actions2.add(() -> stringBuilder.append(2));

            actions1.add(actions2);
            actions1.invoke();

            assertThat(stringBuilder.toString()).isEqualTo("012012");
        }

        @Test
        @DisplayName("应支持嵌套添加 MulticastDelegate")
        void shouldSupportNestedMulticastDelegate() {
            int[] res = {0};
            Actions actions1 = Actions.delegate();
            actions1.add(() -> res[0]++);

            Actions actions2 = Actions.delegate();
            actions2.add(actions1);

            actions1.add(() -> res[0]++);

            actions2.invoke();

            assertThat(res[0]).isEqualTo(1);
        }

        @Test
        @DisplayName("添加 null 时应忽略")
        void shouldIgnoreNullWhenAdding() {
            Actions actions = Actions.delegate();
            actions.add(null);

            assertThat(actions.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("应支持逆变类型添加")
        void shouldSupportContravariantType() {
            Func<Number> func = () -> Double.valueOf(1);
            assertThat(func.invoke()).isEqualTo(1.0d);
        }
    }

    @Nested
    @DisplayName("移除操作")
    class RemoveTests {
        @Test
        @DisplayName("移除后应不再执行")
        void shouldNotExecuteAfterRemoval() {
            StringBuilder stringBuilder = new StringBuilder();
            Actions actions = Actions.delegate();
            Action action = () -> stringBuilder.append(0);

            actions.add(action);
            actions.invoke();
            assertThat(stringBuilder.toString()).isEqualTo("0");

            actions.remove(action);
            actions.invoke();
            assertThat(stringBuilder.toString()).isEqualTo("0");
        }

        @Test
        @DisplayName("应支持移除另一个 MulticastDelegate")
        void shouldSupportRemovingAnotherMulticastDelegate() {
            StringBuilder stringBuilder = new StringBuilder();
            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            Action action0 = () -> stringBuilder.append(0);
            Action action1 = () -> stringBuilder.append(1);
            Action action2 = () -> stringBuilder.append(2);
            Action action3 = () -> stringBuilder.append(3);
            Action action4 = () -> stringBuilder.append(4);

            actions1.add(action0);
            actions1.add(action1);
            actions1.add(action2);
            actions1.add(action3);
            actions1.add(action4);
            actions1.add(action3);

            actions2.add(action3);
            actions2.add(action4);
            actions2.add(action3);

            actions1.remove(actions2);
            actions1.remove(actions2);

            actions1.invoke();

            assertThat(stringBuilder.toString()).isEqualTo("012");
        }

        @Test
        @DisplayName("移除 null 时应忽略")
        void shouldIgnoreNullWhenRemoving() {
            Actions actions = Actions.delegate();
            actions.remove(null);

            assertThat(actions.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("当源元素少于目标时应正常处理")
        void shouldHandleWhenSourceSmallerThanTarget() {
            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            Action actionA = () -> {};
            Action actionB = () -> {};

            actions1.add(actionA);
            actions1.add(actionB);

            actions2.add(actionA);
            actions2.add(actionB);
            actions2.add(() -> {});

            assertThat(actions1.count()).isEqualTo(2);
        }

        @Test
        @DisplayName("应支持部分匹配移除")
        void shouldSupportPartialMatchRemoval() {
            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            StringBuilder sb = new StringBuilder();

            Action actionA = () -> sb.append("A");
            Action actionB = () -> sb.append("B");
            Action actionC = () -> sb.append("C");
            Action actionD = () -> sb.append("D");
            Action actionE = () -> sb.append("E");

            actions1.add(actionA);
            actions1.add(actionB);
            actions1.add(actionC);
            actions1.add(actionD);
            actions1.add(actionE);

            actions2.add(actionB);
            actions2.add(actionC);
            actions2.add(actionD);

            actions1.remove(actions2);

            assertThat(actions1.count()).isEqualTo(2);

            sb.delete(0, sb.length());
            actions1.invoke();
            assertThat(sb.toString()).isEqualTo("AE");
        }

        @Test
        @DisplayName("空源应正常处理")
        void shouldHandleEmptySource() {
            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            Action actionA = () -> {};
            actions2.add(actionA);

            actions1.remove(actions2);

            assertThat(actions1.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("当源元素为1而目标为3时应正常处理")
        void shouldHandleWhenSourceHasOneAndTargetHasThree() {
            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            actions1.add(() -> {});
            actions2.add(() -> {});
            actions2.add(() -> {});
            actions2.add(() -> {});

            actions1.remove(actions2);

            assertThat(actions1.count()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("包含检查")
    class ContainsTests {
        @Test
        @DisplayName("应正确检查委托是否存在")
        void shouldCheckIfDelegateExists() {
            Actions actions = Actions.delegate();
            Action action0 = () -> {};

            actions.add(action0);

            assertThat(actions.contains(action0)).isTrue();

            actions.remove(action0);
            assertThat(actions.contains(action0)).isFalse();
        }

        @Test
        @DisplayName("应正确检查是否包含另一个 MulticastDelegate")
        void shouldCheckIfContainsMulticastDelegate() {
            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            Action action0 = () -> {};
            Action action1 = () -> {};
            Action action2 = () -> {};
            Action action3 = () -> {};
            Action action4 = () -> {};

            actions1.add(action0);
            actions1.add(action1);
            actions1.add(action2);
            actions1.add(action3);
            actions1.add(action4);
            actions1.add(action3);

            actions2.add(action3);
            actions2.add(action4);
            actions2.add(action3);

            assertThat(actions1.contains(actions2)).isTrue();

            actions2.add(action4);
            assertThat(actions1.contains(actions2)).isFalse();
        }

        @Test
        @DisplayName("应正确处理 null 检查")
        void shouldHandleNullCheck() {
            Actions actions = Actions.delegate();
            assertThat(actions.contains(null)).isFalse();
        }

        @Test
        @DisplayName("当源小于目标时应返回 false")
        void shouldReturnFalseWhenSourceSmallerThanTarget() {
            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            Action actionA = () -> {};
            Action actionB = () -> {};

            actions1.add(actionA);
            actions1.add(actionB);

            actions2.add(actionA);
            actions2.add(actionB);
            actions2.add(() -> {});

            assertThat(actions1.contains(actions2)).isFalse();
        }
    }

    @Nested
    @DisplayName("其他操作")
    class OtherOperationTests {
        @Test
        @DisplayName("应正确清空所有委托")
        void shouldClearAllDelegates() {
            StringBuilder stringBuilder = new StringBuilder();
            Actions actions = Actions.delegate();
            actions.add(() -> stringBuilder.append(0));
            actions.add(() -> stringBuilder.append(1));
            actions.clear();
            actions.invoke();

            assertThat(stringBuilder.toString()).isEmpty();
        }

        @Test
        @DisplayName("应支持在内部清空")
        void shouldSupportClearingInside() {
            StringBuilder stringBuilder = new StringBuilder();
            Actions actions = Actions.delegate();
            actions.add(() -> stringBuilder.append(0));
            actions.add(() -> stringBuilder.append(1));
            actions.add(actions::clear);
            actions.add(() -> stringBuilder.append(2));
            actions.invoke();

            assertThat(stringBuilder.toString()).isEqualTo("012");
        }

        @Test
        @DisplayName("应正确计数")
        void shouldCountCorrectly() {
            Actions actions = Actions.delegate();
            assertThat(actions.count()).isEqualTo(0);
            actions.add(() -> {});
            assertThat(actions.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("应返回委托副本")
        void shouldReturnDelegatesCopy() {
            Actions actions = Actions.delegate();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                actions.add(() -> stringBuilder.append(finalI));
            }
            List<Action> delegates = actions.getDelegates();
            for (Action act : delegates) {
                act.invoke();
            }

            assertThat(stringBuilder.toString()).isEqualTo("01234");

            stringBuilder.delete(0, stringBuilder.length());
            delegates.clear();
            assertThat(stringBuilder.toString()).isEmpty();

            stringBuilder.delete(0, stringBuilder.length());
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                delegates.add(() -> stringBuilder.append(finalI));
            }
            for (Action act : delegates) {
                act.invoke();
            }

            assertThat(stringBuilder.toString()).isEqualTo("01234");

            stringBuilder.delete(0, stringBuilder.length());
            actions.invoke();
            assertThat(stringBuilder.toString()).isEqualTo("01234");
        }

        @Test
        @DisplayName("应支持迭代器遍历")
        void shouldSupportIterator() {
            int[] res = {0};
            Actions actions = Actions.delegate();
            actions.add(() -> res[0]++);
            actions.add(() -> res[0]++);
            actions.add(() -> res[0]++);
            actions.add(() -> res[0]++);
            actions.add(() -> res[0]++);

            for (Action action : actions) {
                action.invoke();
            }

            assertThat(res[0]).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("equals 和 hashCode")
    class EqualsAndHashCodeTests {
        @Test
        @DisplayName("相同委托应相等")
        void shouldBeEqualWhenSameDelegates() {
            Action println1 = () -> System.out.println(1);
            Action println2 = () -> System.out.println(2);

            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            actions1.add(println1);
            actions1.add(println2);
            actions2.add(println1);
            actions2.add(println2);

            assertThat(actions1).isEqualTo(actions2);
            assertThat(actions2).isEqualTo(actions1);
        }

        @Test
        @DisplayName("不同委托应不相等")
        void shouldNotBeEqualWhenDifferentDelegates() {
            Action println1 = () -> System.out.println(1);
            Action println2 = () -> System.out.println(2);

            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            actions1.add(println1);
            actions1.add(println2);
            actions2.add(println1);
            actions2.add(println2);
            actions1.add(println1);

            assertThat(actions1).isNotEqualTo(actions2);
            assertThat(actions2).isNotEqualTo(actions1);
        }

        @Test
        @DisplayName("相同委托应有相同 hashCode")
        void shouldHaveSameHashCodeForSameDelegates() {
            Action println1 = () -> System.out.println(1);
            Action println2 = () -> System.out.println(2);

            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            actions1.add(println1);
            actions1.add(println2);
            actions2.add(println1);
            actions2.add(println2);

            assertThat(actions1.hashCode()).isEqualTo(actions2.hashCode());
        }

        @Test
        @DisplayName("不同委托应有不同 hashCode")
        void shouldHaveDifferentHashCodeForDifferentDelegates() {
            Action println1 = () -> System.out.println(1);
            Action println2 = () -> System.out.println(2);

            Actions actions1 = Actions.delegate();
            Actions actions2 = Actions.delegate();

            actions1.add(println1);
            actions1.add(println2);
            actions2.add(println1);
            actions2.add(println2);
            actions1.add(println1);

            assertThat(actions1.hashCode()).isNotEqualTo(actions2.hashCode());
        }
    }

    @Nested
    @DisplayName("并发安全")
    class ConcurrencyTests {
        @Test
        @DisplayName("Actions.event 应线程安全")
        void shouldBeThreadSafe() throws InterruptedException {
            Thread.sleep(5000);

            Actions actions = Actions.event();

            int threadCount = 2;
            int opsPerThread = 100_000;

            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(threadCount);

            Action action = () -> {};

            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < opsPerThread; j++) {
                        actions.add(action);
                        actions.remove(action);
                    }
                    latch.countDown();
                });
            }

            latch.await();
            executor.shutdown();

            actions.invoke();
            List<Action> delegates = actions.getDelegates();
            assertThat(delegates.size()).isEqualTo(0);
        }

        @Test
        @DisplayName("应比较 Delegate 和 Event 的线程安全性")
        void shouldCompareDelegateAndEventThreadSafety() throws InterruptedException {
            final int threadCount = 32;
            final int opsPerThread = 10_000;

            Actions unsafe = Actions.delegate();
            Actions safe = Actions.event();
            Action noop = () -> {};

            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(threadCount * 2);

            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < opsPerThread; j++) {
                        unsafe.add(noop);
                        unsafe.remove(noop);
                    }
                    latch.countDown();
                });
                executor.submit(() -> {
                    for (int j = 0; j < opsPerThread; j++) {
                        safe.add(noop);
                        safe.remove(noop);
                    }
                    latch.countDown();
                });
            }

            latch.await();
            executor.shutdown();

            assertThat(safe.getDelegates().size()).isEqualTo(0);
        }

        @Test
        @DisplayName("多线程调用应正确累加")
        void shouldAccumulateCorrectlyWithMultipleThreads() throws InterruptedException {
            final int threadCount = 16;
            final int actionCount = 5;
            final AtomicInteger counter = new AtomicInteger(0);

            Actions actions = Actions.delegate();
            for (int i = 0; i < actionCount; i++) {
                actions.add(counter::incrementAndGet);
            }

            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < threadCount; i++) {
                threads.add(new Thread(actions::invoke));
            }
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }

            assertThat(counter.get()).isEqualTo(threadCount * actionCount);
        }
    }

    @Nested
    @DisplayName("内存布局")
    class MemoryLayoutTests {
        @Test
        @DisplayName("应正确显示对象内存布局")
        void shouldShowCorrectMemoryLayout() {
            Actions actions = Actions.delegate();
            ClassLayout classLayout = ClassLayout.parseInstance(actions);

            System.out.println(classLayout.toPrintable());
            assertThat(classLayout.instanceSize()).isEqualTo(8 + 4 + 4 + 4 + 4);

            GraphLayout graphLayout = GraphLayout.parseInstance(actions);

            System.out.println(graphLayout.toPrintable());
            assertThat(graphLayout.totalSize()).isEqualTo(16 + 24 + 16);
        }
    }
}
