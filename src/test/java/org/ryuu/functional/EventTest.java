package org.ryuu.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Event")
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

    @Nested
    @DisplayName("单委托操作")
    class SingleDelegateTests {
        @Test
        @DisplayName("添加后应能执行")
        void shouldExecuteAfterAdding() {
            ClassWithActionsEvent withEvent = new ClassWithActionsEvent();
            AtomicBoolean executed = new AtomicBoolean(false);
            Action action = () -> executed.set(true);

            withEvent.getActions().add(action);
            withEvent.invoke();

            assertThat(executed.get()).isTrue();
        }

        @Test
        @DisplayName("移除后应不执行")
        void shouldNotExecuteAfterRemoval() {
            ClassWithActionsEvent withEvent = new ClassWithActionsEvent();
            AtomicBoolean executed = new AtomicBoolean(false);
            Action action = () -> executed.set(true);

            withEvent.getActions().add(action);
            withEvent.getActions().remove(action);
            withEvent.invoke();

            assertThat(executed.get()).isFalse();
        }

        @Test
        @DisplayName("移除后委托列表应为空")
        void shouldHaveEmptyDelegatesListAfterRemoval() {
            ClassWithActionsEvent withEvent = new ClassWithActionsEvent();
            Action action = () -> {};
            withEvent.getActions().add(action);
            withEvent.getActions().remove(action);

            List<Action> delegates = ((Actions) withEvent.getActions()).getDelegates();
            assertThat(delegates).isEmpty();
        }
    }

    @Nested
    @DisplayName("多参数委托")
    class MultiArgDelegateTests {
        @Test
        @DisplayName("8 参数委托应能执行")
        void shouldExecuteWith8Args() {
            EventActions8Args eventActions8Args = new EventActions8Args();
            AtomicBoolean executed = new AtomicBoolean(false);

            Action8Args<String, String, String, String, String, String, String, String> action =
                    (a1, a2, a3, a4, a5, a6, a7, a8) -> executed.set(true);

            eventActions8Args.getActions().add(action);
            eventActions8Args.invoke("1", "2", "3", "4", "5", "6", "7", "8");

            assertThat(executed.get()).isTrue();
        }
    }
}
