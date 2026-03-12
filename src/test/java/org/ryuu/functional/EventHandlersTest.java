package org.ryuu.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EventHandlers")
class EventHandlersTest {
    static class MyEventArgs extends EventArgs {
        final String message;

        MyEventArgs(String message) {
            this.message = message;
        }
    }

    @Nested
    @DisplayName("基本操作")
    class BasicOperationTests {
        @Test
        @DisplayName("应执行所有注册的处理器")
        void shouldInvokeAllRegisteredHandlers() {
            EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

            StringBuilder log = new StringBuilder();

            EventHandler<String, MyEventArgs> h1 = (sender, args) -> log.append("H1:").append(sender).append(":").append(args.message).append(";");
            EventHandler<String, MyEventArgs> h2 = (sender, args) -> log.append("H2:").append(sender).append(":").append(args.message).append(";");

            handlers.add(h1);
            handlers.add(h2);

            handlers.invoke("SenderA", new MyEventArgs("Hello"));

            assertThat(log.toString()).isEqualTo("H1:SenderA:Hello;H2:SenderA:Hello;");
        }

        @Test
        @DisplayName("移除后应不执行")
        void shouldNotInvokeAfterRemoval() {
            EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

            StringBuilder log = new StringBuilder();

            EventHandler<String, MyEventArgs> h1 = (sender, args) -> log.append("H1;");
            EventHandler<String, MyEventArgs> h2 = (sender, args) -> log.append("H2;");

            handlers.add(h1);
            handlers.add(h2);

            handlers.remove(h1);

            handlers.invoke("SenderB", new MyEventArgs("X"));

            assertThat(log.toString()).isEqualTo("H2;");
        }

        @Test
        @DisplayName("空处理器不应抛异常")
        void shouldNotThrowWhenEmptyHandlers() {
            EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

            handlers.invoke("SenderC", new MyEventArgs("NoHandler"));
        }
    }
}
