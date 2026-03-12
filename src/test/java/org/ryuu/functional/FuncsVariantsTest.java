package org.ryuu.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Funcs Multi-Arg Variants Test")
class FuncsVariantsTest {

    @Nested
    @DisplayName("Funcs (no params)")
    class FuncsTests {
        @Test
        @DisplayName("should return last delegate result")
        void shouldReturnLastResult() {
            Funcs<String> funcs = Funcs.delegate();

            funcs.add(() -> "first");
            funcs.add(() -> "second");

            String result = funcs.invoke();

            // Funcs returns the last result, not the first
            assertThat(result).isEqualTo("second");
        }

        @Test
        @DisplayName("should return null when empty")
        void shouldReturnNullWhenEmpty() {
            Funcs<String> funcs = Funcs.delegate();

            String result = funcs.invoke();

            assertThat(result).isNull();
        }

        @Test
        @DisplayName("should invoke all delegates in order")
        void shouldInvokeAllInOrder() {
            Funcs<String> funcs = Funcs.delegate();
            StringBuilder sb = new StringBuilder();

            funcs.add(() -> {
                sb.append("1");
                return "r1";
            });
            funcs.add(() -> {
                sb.append("2");
                return "r2";
            });

            funcs.invoke();

            assertThat(sb.toString()).isEqualTo("12");
        }
    }

    @Nested
    @DisplayName("Funcs1Arg")
    class Funcs1ArgTests {
        @Test
        @DisplayName("should return last delegate result")
        void shouldReturnLastResult() {
            Funcs1Arg<Integer, String> funcs = Funcs1Arg.delegate();

            funcs.add(i -> "first:" + i);
            funcs.add(i -> "second:" + i);

            String result = funcs.invoke(42);

            // Funcs returns the last result, not the first
            assertThat(result).isEqualTo("second:42");
        }

        @Test
        @DisplayName("should return null when empty")
        void shouldReturnNullWhenEmpty() {
            Funcs1Arg<Integer, String> funcs = Funcs1Arg.delegate();

            String result = funcs.invoke(42);

            assertThat(result).isNull();
        }

        @Test
        @DisplayName("should invoke all delegates in order")
        void shouldInvokeAllInOrder() {
            Funcs1Arg<Integer, String> funcs = Funcs1Arg.delegate();
            StringBuilder sb = new StringBuilder();

            funcs.add(i -> {
                sb.append("1");
                return "r1";
            });
            funcs.add(i -> {
                sb.append("2");
                return "r2";
            });

            funcs.invoke(1);

            assertThat(sb.toString()).isEqualTo("12");
        }

        @Test
        @DisplayName("should support generic types")
        void shouldSupportGenericTypes() {
            Funcs1Arg<String, Integer> funcs = Funcs1Arg.delegate();

            funcs.add(s -> s.length());
            funcs.add(s -> s.hashCode());

            Integer result = funcs.invoke("test");

            // Returns last result
            assertThat(result).isEqualTo("test".hashCode());
        }
    }

    @Nested
    @DisplayName("Funcs2Args")
    class Funcs2ArgsTests {
        @Test
        @DisplayName("should return last delegate result")
        void shouldReturnLastResult() {
            Funcs2Args<Integer, Integer, String> funcs = Funcs2Args.delegate();

            funcs.add((a, b) -> "sum:" + (a + b));
            funcs.add((a, b) -> "product:" + (a * b));

            String result = funcs.invoke(3, 4);

            // Returns last result
            assertThat(result).isEqualTo("product:12");
        }

        @Test
        @DisplayName("should return null when empty")
        void shouldReturnNullWhenEmpty() {
            Funcs2Args<Integer, Integer, String> funcs = Funcs2Args.delegate();

            String result = funcs.invoke(3, 4);

            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("Funcs3Args")
    class Funcs3ArgsTests {
        @Test
        @DisplayName("should return last delegate result")
        void shouldReturnLastResult() {
            Funcs3Args<Integer, Integer, Integer, String> funcs = Funcs3Args.delegate();

            funcs.add((a, b, c) -> "sum:" + (a + b + c));
            funcs.add((a, b, c) -> "product:" + (a * b * c));

            String result = funcs.invoke(1, 2, 3);

            // Returns last result
            assertThat(result).isEqualTo("product:6");
        }

        @Test
        @DisplayName("should return null when empty")
        void shouldReturnNullWhenEmpty() {
            Funcs3Args<Integer, Integer, Integer, String> funcs = Funcs3Args.delegate();

            String result = funcs.invoke(1, 2, 3);

            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("Funcs4-8Args")
    class Funcs4To8ArgsTests {
        @Test
        @DisplayName("Funcs4Args should work correctly")
        void funcs4ArgsShouldWork() {
            Funcs4Args<String, String, String, String, String> funcs = Funcs4Args.delegate();

            funcs.add((a, b, c, d) -> a + b + c + d);

            String result = funcs.invoke("a", "b", "c", "d");

            assertThat(result).isEqualTo("abcd");
        }

        @Test
        @DisplayName("Funcs5Args should work correctly")
        void funcs5ArgsShouldWork() {
            Funcs5Args<String, String, String, String, String, String> funcs = Funcs5Args.delegate();

            funcs.add((a, b, c, d, e) -> a + b + c + d + e);

            String result = funcs.invoke("a", "b", "c", "d", "e");

            assertThat(result).isEqualTo("abcde");
        }

        @Test
        @DisplayName("Funcs6Args should work correctly")
        void funcs6ArgsShouldWork() {
            Funcs6Args<String, String, String, String, String, String, String> funcs = Funcs6Args.delegate();

            funcs.add((a1, a2, a3, a4, a5, a6) -> "ok");

            String result = funcs.invoke("1", "2", "3", "4", "5", "6");

            assertThat(result).isEqualTo("ok");
        }

        @Test
        @DisplayName("Funcs7Args should work correctly")
        void funcs7ArgsShouldWork() {
            Funcs7Args<String, String, String, String, String, String, String, String> funcs = Funcs7Args.delegate();

            funcs.add((a1, a2, a3, a4, a5, a6, a7) -> "ok");

            String result = funcs.invoke("1", "2", "3", "4", "5", "6", "7");

            assertThat(result).isEqualTo("ok");
        }

        @Test
        @DisplayName("Funcs8Args should work correctly")
        void funcs8ArgsShouldWork() {
            Funcs8Args<String, String, String, String, String, String, String, String, String> funcs = Funcs8Args.delegate();

            funcs.add((a1, a2, a3, a4, a5, a6, a7, a8) -> "ok");

            String result = funcs.invoke("1", "2", "3", "4", "5", "6", "7", "8");

            assertThat(result).isEqualTo("ok");
        }
    }

    @Nested
    @DisplayName("Funcs Return Type Test")
    class ReturnTypeTests {
        @Test
        @DisplayName("should support primitive return type")
        void shouldSupportPrimitiveReturn() {
            Funcs1Arg<Integer, Integer> funcs = Funcs1Arg.delegate();
            funcs.add(i -> i * 2);

            Integer result = funcs.invoke(21);

            assertThat(result).isEqualTo(42);
        }

        @Test
        @DisplayName("should support wrapper return type")
        void shouldSupportWrapperReturn() {
            Funcs1Arg<Integer, Long> funcs = Funcs1Arg.delegate();
            funcs.add(i -> Long.valueOf(i));

            Long result = funcs.invoke(42);

            assertThat(result).isEqualTo(42L);
        }

        @Test
        @DisplayName("should support Void return type")
        void shouldSupportVoidReturn() {
            Funcs1Arg<Integer, Void> funcs = Funcs1Arg.delegate();
            final AtomicInteger counter = new AtomicInteger(0);

            funcs.add(i -> {
                counter.incrementAndGet();
                return null;
            });

            Void result = funcs.invoke(1);

            assertThat(result).isNull();
            assertThat(counter.get()).isEqualTo(1);
        }

        @Test
        @DisplayName("should support array return type")
        void shouldSupportArrayReturn() {
            Funcs1Arg<Integer, int[]> funcs = Funcs1Arg.delegate();
            funcs.add(i -> new int[]{i, i * 2});

            int[] result = funcs.invoke(5);

            assertThat(result).containsExactly(5, 10);
        }
    }
}
