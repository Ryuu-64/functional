package org.ryuu.functional.util;

import org.junit.jupiter.api.Test;
import org.ryuu.functional.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.ryuu.functional.util.FunctionalCompletableFutureInvokeUtils.*;

class FunctionalCompletableFutureInvokeUtilsTest {
    @Test
    void testActionAsyncAll() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(2);

        // Action without args
        AtomicBoolean flag = new AtomicBoolean(false);
        Action action = () -> flag.set(true);
        invokeAsync(action);
        Thread.sleep(100);
        assertTrue(flag.get());

        flag.set(false);
        invokeAsync(action, executor);
        Thread.sleep(100);
        assertTrue(flag.get());

        // Action1Arg
        AtomicInteger result1 = new AtomicInteger();
        Action1Arg<Integer> act1 = result1::set;
        invokeAsync(act1, 10);
        Thread.sleep(100);
        assertEquals(10, result1.get());

        invokeAsync(act1, 20, executor);
        Thread.sleep(100);
        assertEquals(20, result1.get());

        // Action2Args
        Action2Args<Integer, Integer> act2 = (a, b) -> result1.set(a + b);
        invokeAsync(act2, 3, 4);
        Thread.sleep(100);
        assertEquals(7, result1.get());

        invokeAsync(act2, 5, 6, executor);
        Thread.sleep(100);
        assertEquals(11, result1.get());

        // Action3Args
        Action3Args<Integer, Integer, Integer> act3 = (a, b, c) -> result1.set(a + b + c);
        invokeAsync(act3, 1, 2, 3);
        Thread.sleep(100);
        assertEquals(6, result1.get());

        invokeAsync(act3, 2, 3, 4, executor);
        Thread.sleep(100);
        assertEquals(9, result1.get());

        // Action4Args
        Action4Args<Integer, Integer, Integer, Integer> act4 = (a, b, c, d) -> result1.set(a + b + c + d);
        invokeAsync(act4, 1, 2, 3, 4);
        Thread.sleep(100);
        assertEquals(10, result1.get());

        invokeAsync(act4, 2, 3, 4, 5, executor);
        Thread.sleep(100);
        assertEquals(14, result1.get());

        // Action5Args
        Action5Args<Integer, Integer, Integer, Integer, Integer> act5 = (a, b, c, d, e) -> result1.set(a + b + c + d + e);
        invokeAsync(act5, 1, 2, 3, 4, 5);
        Thread.sleep(100);
        assertEquals(15, result1.get());

        invokeAsync(act5, 2, 3, 4, 5, 6, executor);
        Thread.sleep(100);
        assertEquals(20, result1.get());

        // Action6Args
        Action6Args<Integer, Integer, Integer, Integer, Integer, Integer> act6 = (a, b, c, d, e, f) -> result1.set(a + b + c + d + e + f);
        invokeAsync(act6, 1, 2, 3, 4, 5, 6);
        Thread.sleep(100);
        assertEquals(21, result1.get());

        invokeAsync(act6, 2, 3, 4, 5, 6, 7, executor);
        Thread.sleep(100);
        assertEquals(27, result1.get());

        // Action7Args
        Action7Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer> act7 =
                (a, b, c, d, e, f, g) -> result1.set(a + b + c + d + e + f + g);
        invokeAsync(act7, 1, 2, 3, 4, 5, 6, 7);
        Thread.sleep(100);
        assertEquals(28, result1.get());

        invokeAsync(act7, 2, 3, 4, 5, 6, 7, 8, executor);
        Thread.sleep(100);
        assertEquals(35, result1.get());

        // Action8Args
        Action8Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> act8 =
                (a, b, c, d, e, f, g, h) -> result1.set(a + b + c + d + e + f + g + h);
        invokeAsync(act8, 1, 2, 3, 4, 5, 6, 7, 8);
        Thread.sleep(100);
        assertEquals(36, result1.get());

        invokeAsync(act8, 2, 3, 4, 5, 6, 7, 8, 9, executor);
        Thread.sleep(100);
        assertEquals(44, result1.get());
    }

    @Test
    void testFuncAsyncAll() throws ExecutionException, InterruptedException, TimeoutException {
        Executor executor = Executors.newFixedThreadPool(2);

        // Func
        Func<Integer> func = () -> 42;
        CompletableFuture<Integer> future = invokeAsync(func);
        assertEquals(42, future.get(1, TimeUnit.SECONDS));

        CompletableFuture<Integer> futureExec = invokeAsync(func, executor);
        assertEquals(42, futureExec.get(1, TimeUnit.SECONDS));

        // Func1Arg
        Func1Arg<Integer, Integer> func1 = x -> x * 2;
        assertEquals(84, invokeAsync(func1, 42).get(1, TimeUnit.SECONDS));
        assertEquals(100, invokeAsync(func1, 50, executor).get(1, TimeUnit.SECONDS));

        // Func2Args
        Func2Args<Integer, Integer, Integer> func2 = Integer::sum;
        assertEquals(42, invokeAsync(func2, 10, 32).get(1, TimeUnit.SECONDS));
        assertEquals(50, invokeAsync(func2, 20, 30, executor).get(1, TimeUnit.SECONDS));

        // Func3Args
        Func3Args<Integer, Integer, Integer, Integer> func3 = (a, b, c) -> a + b + c;
        assertEquals(6, invokeAsync(func3, 1, 2, 3).get(1, TimeUnit.SECONDS));
        assertEquals(15, invokeAsync(func3, 4, 5, 6, executor).get(1, TimeUnit.SECONDS));

        // Func4Args
        Func4Args<Integer, Integer, Integer, Integer, Integer> func4 = (a, b, c, d) -> a + b + c + d;
        assertEquals(10, invokeAsync(func4, 1, 2, 3, 4).get(1, TimeUnit.SECONDS));
        assertEquals(26, invokeAsync(func4, 5, 6, 7, 8, executor).get(1, TimeUnit.SECONDS));

        // Func5Args
        Func5Args<Integer, Integer, Integer, Integer, Integer, Integer> func5 = (a, b, c, d, e) -> a + b + c + d + e;
        assertEquals(15, invokeAsync(func5, 1, 2, 3, 4, 5).get(1, TimeUnit.SECONDS));
        assertEquals(40, invokeAsync(func5, 6, 7, 8, 9, 10, executor).get(1, TimeUnit.SECONDS));

        // Func6Args
        Func6Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer> func6 =
                (a, b, c, d, e, f) -> a + b + c + d + e + f;
        assertEquals(21, invokeAsync(func6, 1, 2, 3, 4, 5, 6).get(1, TimeUnit.SECONDS));
        assertEquals(36, invokeAsync(func6, 6, 6, 6, 6, 6, 6, executor).get(1, TimeUnit.SECONDS));

        // Func7Args
        Func7Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> func7 =
                (a, b, c, d, e, f, g) -> a + b + c + d + e + f + g;
        assertEquals(28, invokeAsync(func7, 1, 2, 3, 4, 5, 6, 7).get(1, TimeUnit.SECONDS));
        assertEquals(56, invokeAsync(func7, 8, 8, 8, 8, 8, 8, 8, executor).get(1, TimeUnit.SECONDS));

        // Func8Args
        Func8Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> func8 =
                (a, b, c, d, e, f, g, h) -> a + b + c + d + e + f + g + h;
        assertEquals(36, invokeAsync(func8, 1, 2, 3, 4, 5, 6, 7, 8).get(1, TimeUnit.SECONDS));
        assertEquals(72, invokeAsync(func8, 9, 9, 9, 9, 9, 9, 9, 9, executor).get(1, TimeUnit.SECONDS));
    }
}
