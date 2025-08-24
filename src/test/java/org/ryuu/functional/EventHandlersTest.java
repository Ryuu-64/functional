package org.ryuu.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlersTest {
    static class MyEventArgs extends EventArgs {
        final String message;

        MyEventArgs(String message) {
            this.message = message;
        }
    }

    @Test
    void testInvokeMultipleHandlers() {
        EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

        StringBuilder log = new StringBuilder();

        EventHandler<String, MyEventArgs> h1 = (sender, args) -> log.append("H1:").append(sender).append(":").append(args.message).append(";");
        EventHandler<String, MyEventArgs> h2 = (sender, args) -> log.append("H2:").append(sender).append(":").append(args.message).append(";");

        // 添加 handler
        handlers.add(h1);
        handlers.add(h2);

        // 触发事件
        handlers.invoke("SenderA", new MyEventArgs("Hello"));

        assertEquals("H1:SenderA:Hello;H2:SenderA:Hello;", log.toString());
    }

    @Test
    void testRemoveHandler() {
        EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

        StringBuilder log = new StringBuilder();

        EventHandler<String, MyEventArgs> h1 = (sender, args) -> log.append("H1;");
        EventHandler<String, MyEventArgs> h2 = (sender, args) -> log.append("H2;");

        handlers.add(h1);
        handlers.add(h2);

        // 移除 h1
        handlers.remove(h1);

        handlers.invoke("SenderB", new MyEventArgs("X"));

        assertEquals("H2;", log.toString());
    }

    @Test
    void testEmptyHandlers() {
        EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

        // 不应抛异常
        handlers.invoke("SenderC", new MyEventArgs("NoHandler"));

        // 如果没有 handler，什么都不发生
        assertTrue(true);
    }
}
