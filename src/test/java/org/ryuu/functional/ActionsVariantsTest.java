package org.ryuu.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Actions Multi-Arg Variants Test")
class ActionsVariantsTest {

    @Nested
    @DisplayName("Actions1Arg")
    class Actions1ArgTests {
        @Test
        @DisplayName("should invoke all delegates in order")
        void shouldInvokeAllInOrder() {
            Actions1Arg<String> actions = Actions1Arg.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add(s -> sb.append("1:").append(s).append(";"));
            actions.add(s -> sb.append("2:").append(s).append(";"));

            actions.invoke("test");

            assertThat(sb.toString()).isEqualTo("1:test;2:test;");
        }

        @Test
        @DisplayName("should not throw when invoking empty delegate")
        void shouldNotThrowWhenEmpty() {
            Actions1Arg<String> actions = Actions1Arg.delegate();
            actions.invoke("test");
        }

        @Test
        @DisplayName("should allow duplicate delegates")
        void shouldAllowDuplicateDelegates() {
            Actions1Arg<String> actions = Actions1Arg.delegate();
            Action1Arg<String> action = s -> {};

            actions.add(action);
            actions.add(action);

            assertThat(actions.count()).isEqualTo(2);
        }

        @Test
        @DisplayName("event mode should be thread-safe")
        void eventShouldBeThreadSafe() {
            final Actions1Arg<Integer> actions = Actions1Arg.event();
            final AtomicInteger counter = new AtomicInteger(0);

            for (int i = 0; i < 100; i++) {
                actions.add(new Action1Arg<Integer>() {
                    @Override
                    public void invoke(Integer arg) {
                        counter.incrementAndGet();
                    }
                });
            }

            // Multiple threads invoke simultaneously
            Thread[] threads = new Thread[10];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        actions.invoke(1);
                    }
                });
            }
            for (Thread t : threads) {
                t.start();
            }
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // 10 threads * 100 actions = 1000
            assertThat(counter.get()).isEqualTo(1000);
        }
    }

    @Nested
    @DisplayName("Actions2Args")
    class Actions2ArgsTests {
        @Test
        @DisplayName("should invoke all delegates in order")
        void shouldInvokeAllInOrder() {
            Actions2Args<String, Integer> actions = Actions2Args.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add((s, i) -> sb.append("1:").append(s).append(i).append(";"));
            actions.add((s, i) -> sb.append("2:").append(s).append(i).append(";"));

            actions.invoke("test", 42);

            assertThat(sb.toString()).isEqualTo("1:test42;2:test42;");
        }

        @Test
        @DisplayName("should not throw when invoking empty delegate")
        void shouldNotThrowWhenEmpty() {
            Actions2Args<String, Integer> actions = Actions2Args.delegate();
            actions.invoke("test", 42);
        }
    }

    @Nested
    @DisplayName("Actions3Args")
    class Actions3ArgsTests {
        @Test
        @DisplayName("should invoke all delegates in order")
        void shouldInvokeAllInOrder() {
            Actions3Args<String, Integer, Boolean> actions = Actions3Args.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add((s, i, b) -> sb.append("1:").append(s).append(i).append(b).append(";"));
            actions.add((s, i, b) -> sb.append("2:").append(s).append(i).append(b).append(";"));

            actions.invoke("test", 42, true);

            assertThat(sb.toString()).isEqualTo("1:test42true;2:test42true;");
        }

        @Test
        @DisplayName("should not throw when invoking empty delegate")
        void shouldNotThrowWhenEmpty() {
            Actions3Args<String, Integer, Boolean> actions = Actions3Args.delegate();
            actions.invoke("test", 42, true);
        }
    }

    @Nested
    @DisplayName("Actions4Args")
    class Actions4ArgsTests {
        @Test
        @DisplayName("should invoke all delegates in order")
        void shouldInvokeAllInOrder() {
            Actions4Args<String, Integer, Boolean, String> actions = Actions4Args.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add((a, b, c, d) -> sb.append("1:").append(a).append(b).append(c).append(d).append(";"));

            actions.invoke("a", 1, true, "d");

            assertThat(sb.toString()).isEqualTo("1:a1trued;");
        }
    }

    @Nested
    @DisplayName("Actions5Args")
    class Actions5ArgsTests {
        @Test
        @DisplayName("should invoke all delegates in order")
        void shouldInvokeAllInOrder() {
            Actions5Args<String, Integer, Boolean, String, Double> actions = Actions5Args.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add((a, b, c, d, e) -> sb.append("1:").append(a).append(b).append(c).append(d).append(e).append(";"));

            actions.invoke("a", 1, true, "d", 2.0);

            assertThat(sb.toString()).isEqualTo("1:a1trued2.0;");
        }
    }

    @Nested
    @DisplayName("Actions6-8Args")
    class Actions6To8ArgsTests {
        @Test
        @DisplayName("Actions6Args should work correctly")
        void actions6ArgsShouldWork() {
            Actions6Args<String, String, String, String, String, String> actions = Actions6Args.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add((a1, a2, a3, a4, a5, a6) -> sb.append("ok"));

            actions.invoke("1", "2", "3", "4", "5", "6");

            assertThat(sb.toString()).isEqualTo("ok");
        }

        @Test
        @DisplayName("Actions7Args should work correctly")
        void actions7ArgsShouldWork() {
            Actions7Args<String, String, String, String, String, String, String> actions = Actions7Args.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add((a1, a2, a3, a4, a5, a6, a7) -> sb.append("ok"));

            actions.invoke("1", "2", "3", "4", "5", "6", "7");

            assertThat(sb.toString()).isEqualTo("ok");
        }

        @Test
        @DisplayName("Actions8Args should work correctly")
        void actions8ArgsShouldWork() {
            Actions8Args<String, String, String, String, String, String, String, String> actions = Actions8Args.delegate();
            StringBuilder sb = new StringBuilder();

            actions.add((a1, a2, a3, a4, a5, a6, a7, a8) -> sb.append("ok"));

            actions.invoke("1", "2", "3", "4", "5", "6", "7", "8");

            assertThat(sb.toString()).isEqualTo("ok");
        }
    }
}
