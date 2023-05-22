package org.ryuu.functional;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class BenchMark {
    private Actions actions;
    private int[] results;

    @Setup
    public void setUp() {
        actions = new Actions();
        results = new int[1];
        actions.add(() -> results[0] += 1);
    }

    @Benchmark
    public void invoke() {
        actions.invoke();
    }

    @TearDown
    public void tearDown() {
        System.out.println(Arrays.toString(results));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchMark.class.getName())
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();

        new Runner(options).run();
    }
}
