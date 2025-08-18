package org.ryuu.functional;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@State(Scope.Benchmark)
public class BenchMark {
    private Actions actions;
    private AtomicInteger result;

    @Setup
    public void setUp() {
        result = new AtomicInteger();
        actions = new Actions();
        actions.add(result::incrementAndGet);
    }

    @Benchmark
    public void invoke() {
        actions.invoke();
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        System.out.println("result = " + result.get());
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