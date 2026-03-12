package org.ryuu.functional.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.ryuu.functional.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ryuu.functional.util.FunctionalCompletableFutureInvokeUtils.invokeAsync;

@DisplayName("FunctionalCompletableFutureInvokeUtils")
class FunctionalCompletableFutureInvokeUtilsTest {

    @Nested
    @DisplayName("异步 Action 调用")
    class AsyncActionTests {
        @Test
        @DisplayName("应异步执行无参数 Action")
        void shouldInvokeAsyncActionWithoutArgs() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicBoolean flag = new AtomicBoolean(false);
            Action action = () -> flag.set(true);
            invokeAsync(action);
            Thread.sleep(100);
            assertThat(flag.get()).isTrue();

            flag.set(false);
            invokeAsync(action, executor);
            Thread.sleep(100);
            assertThat(flag.get()).isTrue();
        }

        @Test
        @DisplayName("应异步执行单参数 Action")
        void shouldInvokeAsyncAction1Arg() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action1Arg<Integer> action1 = result::set;
            invokeAsync(action1, 10);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(10);

            invokeAsync(action1, 20, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(20);
        }

        @Test
        @DisplayName("应异步执行双参数 Action")
        void shouldInvokeAsyncAction2Args() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action2Args<Integer, Integer> action2 = (a, b) -> result.set(a + b);
            invokeAsync(action2, 3, 4);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(7);

            invokeAsync(action2, 5, 6, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(11);
        }

        @Test
        @DisplayName("应异步执行三参数 Action")
        void shouldInvokeAsyncAction3Args() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action3Args<Integer, Integer, Integer> action3 = (a, b, c) -> result.set(a + b + c);
            invokeAsync(action3, 1, 2, 3);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(6);

            invokeAsync(action3, 2, 3, 4, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(9);
        }

        @Test
        @DisplayName("应异步执行四参数 Action")
        void shouldInvokeAsyncAction4Args() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action4Args<Integer, Integer, Integer, Integer> action4 = (a, b, c, d) -> result.set(a + b + c + d);
            invokeAsync(action4, 1, 2, 3, 4);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(10);

            invokeAsync(action4, 2, 3, 4, 5, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(14);
        }

        @Test
        @DisplayName("应异步执行五参数 Action")
        void shouldInvokeAsyncAction5Args() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action5Args<Integer, Integer, Integer, Integer, Integer> action5 = (a, b, c, d, e) -> result.set(a + b + c + d + e);
            invokeAsync(action5, 1, 2, 3, 4, 5);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(15);

            invokeAsync(action5, 2, 3, 4, 5, 6, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(20);
        }

        @Test
        @DisplayName("应异步执行六参数 Action")
        void shouldInvokeAsyncAction6Args() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action6Args<Integer, Integer, Integer, Integer, Integer, Integer> action6 = (a, b, c, d, e, f) -> result.set(a + b + c + d + e + f);
            invokeAsync(action6, 1, 2, 3, 4, 5, 6);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(21);

            invokeAsync(action6, 2, 3, 4, 5, 6, 7, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(27);
        }

        @Test
        @DisplayName("应异步执行七参数 Action")
        void shouldInvokeAsyncAction7Args() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action7Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer> action7 =
                    (a, b, c, d, e, f, g) -> result.set(a + b + c + d + e + f + g);
            invokeAsync(action7, 1, 2, 3, 4, 5, 6, 7);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(28);

            invokeAsync(action7, 2, 3, 4, 5, 6, 7, 8, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(35);
        }

        @Test
        @DisplayName("应异步执行八参数 Action")
        void shouldInvokeAsyncAction8Args() throws InterruptedException {
            Executor executor = Executors.newFixedThreadPool(2);

            AtomicInteger result = new AtomicInteger();
            Action8Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> action8 =
                    (a, b, c, d, e, f, g, h) -> result.set(a + b + c + d + e + f + g + h);
            invokeAsync(action8, 1, 2, 3, 4, 5, 6, 7, 8);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(36);

            invokeAsync(action8, 2, 3, 4, 5, 6, 7, 8, 9, executor);
            Thread.sleep(100);
            assertThat(result.get()).isEqualTo(44);
        }
    }

    @Nested
    @DisplayName("异步 Func 调用")
    class AsyncFuncTests {
        @Test
        @DisplayName("应异步执行无参数 Func")
        void shouldInvokeAsyncFuncWithoutArgs() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func<Integer> func = () -> 42;
            CompletableFuture<Integer> future = invokeAsync(func);
            assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo(42);

            CompletableFuture<Integer> futureExec = invokeAsync(func, executor);
            assertThat(futureExec.get(1, TimeUnit.SECONDS)).isEqualTo(42);
        }

        @Test
        @DisplayName("应异步执行单参数 Func")
        void shouldInvokeAsyncFunc1Arg() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func1Arg<Integer, Integer> func1 = x -> x * 2;
            assertThat(invokeAsync(func1, 42).get(1, TimeUnit.SECONDS)).isEqualTo(84);
            assertThat(invokeAsync(func1, 50, executor).get(1, TimeUnit.SECONDS)).isEqualTo(100);
        }

        @Test
        @DisplayName("应异步执行双参数 Func")
        void shouldInvokeAsyncFunc2Args() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func2Args<Integer, Integer, Integer> func2 = Integer::sum;
            assertThat(invokeAsync(func2, 10, 32).get(1, TimeUnit.SECONDS)).isEqualTo(42);
            assertThat(invokeAsync(func2, 20, 30, executor).get(1, TimeUnit.SECONDS)).isEqualTo(50);
        }

        @Test
        @DisplayName("应异步执行三参数 Func")
        void shouldInvokeAsyncFunc3Args() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func3Args<Integer, Integer, Integer, Integer> func3 = (a, b, c) -> a + b + c;
            assertThat(invokeAsync(func3, 1, 2, 3).get(1, TimeUnit.SECONDS)).isEqualTo(6);
            assertThat(invokeAsync(func3, 4, 5, 6, executor).get(1, TimeUnit.SECONDS)).isEqualTo(15);
        }

        @Test
        @DisplayName("应异步执行四参数 Func")
        void shouldInvokeAsyncFunc4Args() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func4Args<Integer, Integer, Integer, Integer, Integer> func4 = (a, b, c, d) -> a + b + c + d;
            assertThat(invokeAsync(func4, 1, 2, 3, 4).get(1, TimeUnit.SECONDS)).isEqualTo(10);
            assertThat(invokeAsync(func4, 5, 6, 7, 8, executor).get(1, TimeUnit.SECONDS)).isEqualTo(26);
        }

        @Test
        @DisplayName("应异步执行五参数 Func")
        void shouldInvokeAsyncFunc5Args() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func5Args<Integer, Integer, Integer, Integer, Integer, Integer> func5 = (a, b, c, d, e) -> a + b + c + d + e;
            assertThat(invokeAsync(func5, 1, 2, 3, 4, 5).get(1, TimeUnit.SECONDS)).isEqualTo(15);
            assertThat(invokeAsync(func5, 6, 7, 8, 9, 10, executor).get(1, TimeUnit.SECONDS)).isEqualTo(40);
        }

        @Test
        @DisplayName("应异步执行六参数 Func")
        void shouldInvokeAsyncFunc6Args() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func6Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer> func6 =
                    (a, b, c, d, e, f) -> a + b + c + d + e + f;
            assertThat(invokeAsync(func6, 1, 2, 3, 4, 5, 6).get(1, TimeUnit.SECONDS)).isEqualTo(21);
            assertThat(invokeAsync(func6, 6, 6, 6, 6, 6, 6, executor).get(1, TimeUnit.SECONDS)).isEqualTo(36);
        }

        @Test
        @DisplayName("应异步执行七参数 Func")
        void shouldInvokeAsyncFunc7Args() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func7Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> func7 =
                    (a, b, c, d, e, f, g) -> a + b + c + d + e + f + g;
            assertThat(invokeAsync(func7, 1, 2, 3, 4, 5, 6, 7).get(1, TimeUnit.SECONDS)).isEqualTo(28);
            assertThat(invokeAsync(func7, 8, 8, 8, 8, 8, 8, 8, executor).get(1, TimeUnit.SECONDS)).isEqualTo(56);
        }

        @Test
        @DisplayName("应异步执行八参数 Func")
        void shouldInvokeAsyncFunc8Args() throws ExecutionException, InterruptedException, TimeoutException {
            Executor executor = Executors.newFixedThreadPool(2);

            Func8Args<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> func8 =
                    (a, b, c, d, e, f, g, h) -> a + b + c + d + e + f + g + h;
            assertThat(invokeAsync(func8, 1, 2, 3, 4, 5, 6, 7, 8).get(1, TimeUnit.SECONDS)).isEqualTo(36);
            assertThat(invokeAsync(func8, 9, 9, 9, 9, 9, 9, 9, 9, executor).get(1, TimeUnit.SECONDS)).isEqualTo(72);
        }
    }
}
