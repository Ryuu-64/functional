package org.ryuu.functional;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

// Single-threaded scenarios use ns/op for more intuitive results
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 8)
@Measurement(iterations = 8)
@Fork(8)
public class SingleThreadInvokeBenchmark {
    private static final Action NO_OPERATION = () -> {
    };

    @org.openjdk.jmh.annotations.State(Scope.Thread)
    public static class State {
        @Param({"1", "32"})
        int baseActions;

        final Actions actions = Actions.event();

        @Setup
        public void setup() {
            for (int i = 0; i < baseActions; i++) {
                actions.add(NO_OPERATION);
            }
        }
    }

    @Benchmark
    public void invoke(State s) {
        s.actions.invoke();
    }
}
