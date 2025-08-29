package org.ryuu.functional;

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

import static org.junit.jupiter.api.Assertions.*;

class MulticastDelegateTest {
    @Test
    void testEventThreadSafety() throws InterruptedException {
        // Wait for the biased locking delay period to end (default 4 seconds)
        Thread.sleep(5000);

        Actions actions = Actions.event();

        int threadCount = 2;
        int opsPerThread = 100_000;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Action action = () -> {
        };

        System.out.println("=== Before test ===");
        System.out.println(ClassLayout.parseInstance(getLockObject(actions)).toPrintable());

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < opsPerThread; j++) {
                    actions.add(action);
                    actions.remove(action);

                    if (j % 50_000 == 0) {
                        synchronized (actions) {
                            System.out.println(Thread.currentThread().getName() + " holds lock:");
                            System.out.println(ClassLayout.parseInstance(getLockObject(actions)).toPrintable());
                        }
                    }
                }
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        System.out.println("=== After test ===");
        System.out.println(ClassLayout.parseInstance(getLockObject(actions)).toPrintable());

        // Trigger a call to verify that there is no concurrency exception
        actions.invoke();
        List<Action> delegates = actions.getDelegates();
        assertEquals(0, delegates.size());
    }

    @Test
    void testDelegateVsEventThreadSafety() throws InterruptedException {
        final int threadCount = 32;
        final int opsPerThread = 10_000;

        Actions unsafe = Actions.delegate();
        Actions safe = Actions.event();
        Action noop = () -> {
        };

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

        System.out.println("Unsafe final size: " + unsafe.getDelegates().size());
        assertEquals(0, safe.getDelegates().size());
    }

    @Test
    void invokeMultiThreadSafe() throws InterruptedException {
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

        assertEquals(threadCount * actionCount, counter.get());
    }

    @Test
    void addDelegate() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions1 = Actions.delegate();
        actions1.add(() -> stringBuilder.append(0));
        actions1.add(() -> stringBuilder.append(1));
        actions1.add(() -> stringBuilder.append(2));
        actions1.invoke();
        assertEquals("012", stringBuilder.toString());

        stringBuilder.delete(0, stringBuilder.length());
        Actions actions2 = Actions.delegate();
        actions2.add(() -> stringBuilder.append(0));
        actions2.add(() -> stringBuilder.append(1));
        actions2.add(() -> stringBuilder.append(2));
        actions2.invoke();
        assertEquals("012", stringBuilder.toString());

        stringBuilder.delete(0, stringBuilder.length());
        actions1.add(actions2);
        actions1.invoke();
        assertEquals("012012", stringBuilder.toString());
    }

    @Test
    void addMulticastDelegate() {
        final int[] res = {0};
        Actions actions1 = Actions.delegate();
        actions1.add(() -> res[0]++);

        Actions actions2 = Actions.delegate();
        actions2.add(actions1);

        actions1.add(() -> res[0]++);

        actions2.invoke();
        assertEquals(1, res[0]);
    }

    @Test
    void addNull() {
        Actions actions = Actions.delegate();
        actions.add(null);
        assertEquals(0, actions.count());
    }

    @SuppressWarnings("UnnecessaryBoxing")
    @Test
    void addContravariant() {
        Func<Number> func = () -> Double.valueOf(1);
        assertEquals(1.0d, func.invoke());
    }

    @Test
    void removeDelegate() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions = Actions.delegate();

        Action action = () -> stringBuilder.append(0);

        actions.add(action);

        actions.invoke();
        assertEquals("0", stringBuilder.toString());

        actions.remove(action);

        actions.invoke();
        assertEquals("0", stringBuilder.toString());

        actions.remove(action);
    }

    @Test
    void removeMulticastDelegate() {
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
        assertEquals("012", stringBuilder.toString());
    }

    @Test
    void removeNull() {
        Actions actions = Actions.delegate();
        actions.remove(null);
        assertEquals(0, actions.count());
    }

    @Test
    void containsDelegate() {
        Actions actions1 = Actions.delegate();

        Action action0 = () -> {
        };

        actions1.add(action0);

        assertTrue(actions1.contains(action0));

        actions1.remove(action0);
        assertFalse(actions1.contains(action0));
    }

    @Test
    void containsMulticastDelegate() {
        Actions actions1 = Actions.delegate();
        Actions actions2 = Actions.delegate();

        Action action0 = () -> {
        };
        Action action1 = () -> {
        };
        Action action2 = () -> {
        };
        Action action3 = () -> {
        };
        Action action4 = () -> {
        };

        actions1.add(action0);
        actions1.add(action1);
        actions1.add(action2);
        actions1.add(action3);
        actions1.add(action4);
        actions1.add(action3);

        actions2.add(action3);
        actions2.add(action4);
        actions2.add(action3);

        assertTrue(actions1.contains(actions2));

        actions2.add(action4);
        assertFalse(actions1.contains(actions2));
    }

    @Test
    void containsNull() {
        Actions actions = Actions.delegate();
        assertFalse(actions.contains(null));
    }

    @Test
    void clear() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions = Actions.delegate();
        actions.add(() -> stringBuilder.append(0));
        actions.add(() -> stringBuilder.append(1));
        actions.clear();
        actions.invoke();
        assertEquals("", stringBuilder.toString());
    }

    @Test
    void cleanTheMulticastInTheMulticast() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions = Actions.delegate();
        actions.add(() -> stringBuilder.append(0));
        actions.add(() -> stringBuilder.append(1));
        actions.add(actions::clear);
        actions.add(() -> stringBuilder.append(2));
        actions.invoke();
        assertEquals("012", stringBuilder.toString());
    }

    @Test
    void count() {
        Actions actions = Actions.delegate();
        assertEquals(0, actions.count());
        actions.add(() -> {
        });
        assertEquals(1, actions.count());
    }

    @Test
    void getDelegates() {
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
        assertEquals("01234", stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());

        // copy list remove
        delegates.clear();
        assertEquals("", stringBuilder.toString());

        stringBuilder.delete(0, stringBuilder.length());
        // copy list add
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            delegates.add(() -> stringBuilder.append(finalI));
        }
        for (Action act : delegates) {
            act.invoke();
        }
        assertEquals("01234", stringBuilder.toString());

        stringBuilder.delete(0, stringBuilder.length());
        actions.invoke();
        // origin functional list will not change
        assertEquals("01234", stringBuilder.toString());
    }

    @Test
    void iterator() {
        final int[] res = {0};
        Actions actions = Actions.delegate();
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        for (Action action : actions) {
            action.invoke();
        }
        assertEquals(5, res[0]);
    }

    @Test
    void testEquals() {
        Action println1 = () -> System.out.println(1);
        Action println2 = () -> System.out.println(2);

        Actions actions1 = Actions.delegate();
        Actions actions2 = Actions.delegate();

        actions1.add(println1);
        actions1.add(println2);
        actions2.add(println1);
        actions2.add(println2);
        assertEquals(actions1, actions2);
        assertEquals(actions2, actions1);

        actions1.add(println1);
        assertNotEquals(actions1, actions2);
        assertNotEquals(actions2, actions1);
    }

    @Test
    void testHashCode() {
        Action println1 = () -> System.out.println(1);
        Action println2 = () -> System.out.println(2);
        Actions actions1 = Actions.delegate();
        Actions actions2 = Actions.delegate();
        actions1.add(println1);
        actions1.add(println2);
        actions2.add(println1);
        actions2.add(println2);
        assertEquals(actions1.hashCode(), actions2.hashCode());

        actions1.add(println1);
        assertNotEquals(actions1.hashCode(), actions2.hashCode());
    }

    @Test
    void javaObjectLayout() {
        Actions actions = Actions.delegate();
        ClassLayout classLayout = ClassLayout.parseInstance(actions);
        // org.ryuu.functional.Actions object internals:
        // OFF  SZ                                                    TYPE DESCRIPTION                         VALUE
        //   0   8                                                         (object header: mark)               0x0000000000000001 (non-biasable; age: 0)
        //   8   4                                                         (object header: class)              0x200336d2
        //  12   4   org.ryuu.functional.MulticastDelegate.DelegatesHolder MulticastDelegate.delegatesHolder   (object)
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        System.out.println(classLayout.toPrintable());
        assertEquals(8 + 4 + 4, classLayout.instanceSize());
        GraphLayout graphLayout = GraphLayout.parseInstance(actions);
        // org.ryuu.functional.Actions@4a8355ddd object externals:
        //          ADDRESS       SIZE TYPE                                                       PATH                           VALUE
        //         f55870e8         16 java.util.Collections$EmptyList                            .delegatesHolder.delegates     (object)
        //         f55870f8   64887648 (something else)                                           (somewhere else)               (something else)
        //         f9368c58         16 org.ryuu.functional.Actions                                                               (object)
        //         f9368c68         16 org.ryuu.functional.MulticastDelegate$PlainDelegatesHolder .delegatesHolder               (object)
        System.out.println(graphLayout.toPrintable());
        assertEquals(16 + 16 + 16, graphLayout.totalSize());
    }

    private static Object getLockObject(Actions actions) {
        Field field;
        try {
            field = MulticastDelegate.class.getDeclaredField("delegatesHolder");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        Object lock;
        try {
            lock = field.get(actions);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return lock;
    }
}
