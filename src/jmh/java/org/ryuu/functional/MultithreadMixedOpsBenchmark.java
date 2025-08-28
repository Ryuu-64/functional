package org.ryuu.functional;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Mixed workload: multiple threads execute invoke / few add / few remove simultaneously.
 * Use JMH @Group to put different operation roles into the same competitive group for realistic concurrent interleaving.
 */
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(16)
@Warmup(iterations = 8)
@Measurement(iterations = 8)
@Fork(8)
public class MultithreadMixedOpsBenchmark {
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
        final ConcurrentLinkedQueue<Action> addedQueue = new ConcurrentLinkedQueue<>();
        final AtomicInteger counter = new AtomicInteger();

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
    @GroupThreads(12) // Can adjust ratio based on total thread count
    @Benchmark
    public void invoke(GroupState s) {
        s.actions.invoke();
    }

    /**
     * Few additions: generate independent Action (anonymous class ensures object uniqueness), enqueue for later removal.
     * Control queue upper limit to avoid unlimited growth affecting results; if full, return directly (skip this add).
     */
    @Group("mix")
    @GroupThreads(2)
    @Benchmark
    public void add(GroupState s) {
        if (s.addedQueue.size() >= 1024) { // Upper limit control (can be parameterized)
            return;
        }
        // Anonymous class -> unique instance, avoid duplicate references affecting remove semantics
        // Minor side effect to prevent optimization
        Action a = s.counter::incrementAndGet;
        s.actions.add(a);
        s.addedQueue.offer(a);
    }

    /**
     * Few removals: get an already-added Action from queue and remove it; skip if temporarily none available.
     */
    @Group("mix")
    @GroupThreads(2)
    @Benchmark
    public void remove(GroupState s) {
        Action a = s.addedQueue.poll();
        if (a == null) {
            // If none available, add a new one then delete it, ensuring each call actually triggers remove
            a = () -> {
            };
            s.actions.add(a);
        }
        s.actions.remove(a);
    }
}
