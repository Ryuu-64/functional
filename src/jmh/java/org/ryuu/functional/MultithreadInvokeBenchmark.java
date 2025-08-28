package org.ryuu.functional;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Benchmark                       (baseActions)   Mode  Cnt    Score   Error   Units
 * MultithreadInvokeBenchmark.mix              1  thrpt   64  380.979 ± 8.705  ops/us
 */
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(16)
@Warmup(iterations = 8)
@Measurement(iterations = 8)
@Fork(8)
public class MultithreadInvokeBenchmark {
    private static final Action NO_OPERATION = () -> {
    };

    /**
     * Shared state for each group (isolated across different forks/iterations) to avoid cross-group pollution.
     */
    @State(Scope.Group)
    public static class GroupState {
        // Initial delegate count, can be increased to amplify invoke cost and scheduling differences
        @Param({"1"})
        private int baseActions;

        final Actions actions = Actions.event();

        @Setup
        public void setup() {
            // Pre-populate with baseActions NO_OPs to simulate existing subscription scale
            for (int i = 0; i < baseActions; i++) {
                actions.add(NO_OPERATION);
            }
        }
    }

    /**
     * Main invocation role: the majority of threads only dispatch.
     */
    @Group("mix")
    @GroupThreads(16) // Can adjust ratio based on total thread count
    @Benchmark
    public void invoke(GroupState s) {
        s.actions.invoke();
    }
}
