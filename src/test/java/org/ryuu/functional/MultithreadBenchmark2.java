package org.ryuu.functional;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MultithreadBenchmark2 {
    private static final Action NO_OP = () -> {
    };

    /**
     * Shared state for each group (isolated across different forks/iterations) to avoid cross-group pollution.
     */
    @State(Scope.Group)
    public static class GroupState {
        @Param({"32"}) // Initial delegate count, can be increased to amplify invoke cost and scheduling differences
        public int baseActions;

        final Actions actions = Actions.event();

        @Setup
        public void setup() {
            // Pre-populate with baseActions NO_OPs to simulate existing subscription scale
            for (int i = 0; i < baseActions; i++) {
                actions.add(NO_OP);
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

    /**
     * Convenient direct execution: specify total threads matching GroupThreads.
     */
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(MultithreadBenchmark2.class.getName())
                .warmupIterations(8)
                .measurementIterations(8)
                .forks(8)
                .threads(16) // Must be >= sum of all group internal threads; set to exactly 16
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();
        new Runner(opt).run();
    }
}
