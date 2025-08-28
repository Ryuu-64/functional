package org.ryuu.functional;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private static class ClassWithActionsEvent {
        private final Actions actions = Actions.event();

        public Event<Action> getActions() {
            return actions;
        }

        public void invoke() {
            actions.invoke();
        }
    }

    private static class EventActions8Args {
        private final Actions8Args<String, String, String, String, String, String, String, String> actions = Actions8Args.event();

        public Event<Action8Args<String, String, String, String, String, String, String, String>> getActions() {
            return actions;
        }

        public void invoke(
                String arg1, String arg2, String arg3, String arg4,
                String arg5, String arg6, String arg7, String arg8
        ) {
            actions.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
    }

    @Test
    void addAndInvoke_singleDelegate_shouldBeExecuted() {
        ClassWithActionsEvent withEvent = new ClassWithActionsEvent();
        AtomicBoolean executed = new AtomicBoolean(false);
        Action action = () -> executed.set(true);

        withEvent.getActions().add(action);
        withEvent.invoke();

        assertTrue(executed.get(), "Delegate should be executed after invocation");
    }

    @Test
    void removeDelegate_shouldNotBeExecuted() {
        ClassWithActionsEvent withEvent = new ClassWithActionsEvent();
        AtomicBoolean executed = new AtomicBoolean(false);
        Action action = () -> executed.set(true);

        withEvent.getActions().add(action);
        withEvent.getActions().remove(action);
        withEvent.invoke();

        assertFalse(executed.get(), "Delegate should not be executed after removal");
    }

    @Test
    void delegatesList_shouldBeEmptyAfterRemove() {
        ClassWithActionsEvent withEvent = new ClassWithActionsEvent();
        Action action = () -> {
        };
        withEvent.getActions().add(action);
        withEvent.getActions().remove(action);

        List<Action> delegates = ((Actions) withEvent.getActions()).getDelegates();
        assertEquals(0, delegates.size(), "Delegates list should be empty after removing the only action");
    }

    @Test
    void addAndInvoke_delegateWith8Args_shouldBeExecuted() {
        EventActions8Args eventActions8Args = new EventActions8Args();
        AtomicBoolean executed = new AtomicBoolean(false);

        Action8Args<String, String, String, String, String, String, String, String> action =
                (a1, a2, a3, a4, a5, a6, a7, a8) -> executed.set(true);

        eventActions8Args.getActions().add(action);
        eventActions8Args.invoke("1", "2", "3", "4", "5", "6", "7", "8");

        assertTrue(executed.get(), "Delegate with 8 args should be executed");
    }
}
