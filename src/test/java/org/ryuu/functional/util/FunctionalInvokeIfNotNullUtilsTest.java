package org.ryuu.functional.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.ryuu.functional.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ryuu.functional.util.FunctionalInvokeIfNotNullUtils.*;

@DisplayName("FunctionalInvokeIfNotNullUtils")
class FunctionalInvokeIfNotNullUtilsTest {
    private final String defaultValue = "invokeNonNull";

    @Nested
    @DisplayName("Actions 调用")
    class ActionsTests {
        @Test
        @DisplayName("应执行非空的 Actions")
        void shouldInvokeWhenNotNull() {
            String[] string = new String[1];
            Actions actions = Actions.delegate();
            actions.add(() -> string[0] = defaultValue);
            invokeIfNotNull(actions);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("null 时应不抛异常")
        void shouldNotThrowWhenNull() {
            Actions actions = Actions.delegate();
            invokeIfNotNull(actions);
        }

        @Test
        @DisplayName("应执行非空的 Actions1Arg")
        void shouldInvoke1ArgWhenNotNull() {
            String[] string = new String[1];
            Actions1Arg<String> actions1Arg = Actions1Arg.delegate();
            actions1Arg.add(arg -> string[0] = arg);
            invokeIfNotNull(actions1Arg, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions1Arg 参数为 null 时应不抛异常")
        void shouldNotThrow1ArgWhenNull() {
            Actions1Arg<String> actions1Arg = Actions1Arg.delegate();
            invokeIfNotNull(actions1Arg, null);
        }

        @Test
        @DisplayName("应执行非空的 Actions2Args")
        void shouldInvoke2ArgsWhenNotNull() {
            String[] string = new String[1];
            Actions2Args<String, String> actions2Args = Actions2Args.delegate();
            actions2Args.add((arg1, arg2) -> string[0] = arg1);
            invokeIfNotNull(actions2Args, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions2Args 参数为 null 时应不抛异常")
        void shouldNotThrow2ArgsWhenNull() {
            Actions2Args<String, String> actions2Args = Actions2Args.delegate();
            invokeIfNotNull(actions2Args, null, null);
        }

        @Test
        @DisplayName("应执行非空的 Actions3Args")
        void shouldInvoke3ArgsWhenNotNull() {
            String[] string = new String[1];
            Actions3Args<String, String, String> actions3Args = Actions3Args.delegate();
            actions3Args.add((arg1, arg2, arg3) -> string[0] = arg1);
            invokeIfNotNull(actions3Args, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions3Args 参数为 null 时应不抛异常")
        void shouldNotThrow3ArgsWhenNull() {
            Actions3Args<String, String, String> actions3Args = Actions3Args.delegate();
            invokeIfNotNull(actions3Args, null, null, null);
        }

        @Test
        @DisplayName("应执行非空的 Actions4Args")
        void shouldInvoke4ArgsWhenNotNull() {
            String[] string = new String[1];
            Actions4Args<String, String, String, String> actions4Args = Actions4Args.delegate();
            actions4Args.add((arg1, arg2, arg3, arg4) -> string[0] = arg1);
            invokeIfNotNull(actions4Args, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions4Args 参数为 null 时应不抛异常")
        void shouldNotThrow4ArgsWhenNull() {
            Actions4Args<String, String, String, String> actions4Args = Actions4Args.delegate();
            invokeIfNotNull(actions4Args, null, null, null, null);
        }

        @Test
        @DisplayName("应执行非空的 Actions5Args")
        void shouldInvoke5ArgsWhenNotNull() {
            String[] string = new String[1];
            Actions5Args<String, String, String, String, String> actions5Args = Actions5Args.delegate();
            actions5Args.add((arg1, arg2, arg3, arg4, arg5) -> string[0] = arg1);
            invokeIfNotNull(actions5Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions5Args 参数为 null 时应不抛异常")
        void shouldNotThrow5ArgsWhenNull() {
            Actions5Args<String, String, String, String, String> actions5Args = Actions5Args.delegate();
            invokeIfNotNull(actions5Args, null, null, null, null, null);
        }

        @Test
        @DisplayName("应执行非空的 Actions6Args")
        void shouldInvoke6ArgsWhenNotNull() {
            String[] string = new String[1];
            Actions6Args<String, String, String, String, String, String> actions6Args = Actions6Args.delegate();
            actions6Args.add((arg1, arg2, arg3, arg4, arg5, arg6) -> string[0] = arg1);
            invokeIfNotNull(actions6Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions6Args 参数为 null 时应不抛异常")
        void shouldNotThrow6ArgsWhenNull() {
            Actions6Args<String, String, String, String, String, String> actions6Args = Actions6Args.delegate();
            invokeIfNotNull(actions6Args, null, null, null, null, null, null);
        }

        @Test
        @DisplayName("应执行非空的 Actions7Args")
        void shouldInvoke7ArgsWhenNotNull() {
            String[] string = new String[1];
            Actions7Args<String, String, String, String, String, String, String> actions7Args = Actions7Args.delegate();
            actions7Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7) -> string[0] = arg1);
            invokeIfNotNull(actions7Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions7Args 参数为 null 时应不抛异常")
        void shouldNotThrow7ArgsWhenNull() {
            Actions7Args<String, String, String, String, String, String, String> actions7Args = Actions7Args.delegate();
            invokeIfNotNull(actions7Args, null, null, null, null, null, null, null);
        }

        @Test
        @DisplayName("应执行非空的 Actions8Args")
        void shouldInvoke8ArgsWhenNotNull() {
            String[] string = new String[1];
            Actions8Args<String, String, String, String, String, String, String, String> actions8Args = Actions8Args.delegate();
            actions8Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> string[0] = arg1);
            invokeIfNotNull(actions8Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Actions8Args 参数为 null 时应不抛异常")
        void shouldNotThrow8ArgsWhenNull() {
            Actions8Args<String, String, String, String, String, String, String, String> actions8Args = Actions8Args.delegate();
            invokeIfNotNull(actions8Args, null, null, null, null, null, null, null, null);
        }
    }

    @Nested
    @DisplayName("Funcs 调用")
    class FuncsTests {
        @Test
        @DisplayName("应返回非空 Funcs 的结果")
        void shouldReturnWhenNotNull() {
            Funcs<String> funcs = Funcs.delegate();
            funcs.add(() -> defaultValue);
            String returnValue = invokeIfNotNull(funcs);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @SuppressWarnings("ConstantValue")
        @Test
        @DisplayName("null 时应返回 null")
        void shouldReturnNullWhenNull() {
            Funcs<String> funcs = null;
            String returnValue = invokeIfNotNull(funcs);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs1Arg 的结果")
        void shouldReturn1ArgWhenNotNull() {
            String[] string = new String[1];
            Funcs1Arg<String, String> funcs1Arg = Funcs1Arg.delegate();
            funcs1Arg.add(arg -> {
                string[0] = arg;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs1Arg, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs1Arg 参数为 null 时应返回 null")
        void shouldReturnNull1ArgWhenNull() {
            Funcs1Arg<String, String> funcs1Arg = Funcs1Arg.delegate();
            String returnValue = invokeIfNotNull(funcs1Arg, null);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs2Args 的结果")
        void shouldReturn2ArgsWhenNotNull() {
            String[] string = new String[1];
            Funcs2Args<String, String, String> funcs2Args = Funcs2Args.delegate();
            funcs2Args.add((arg1, arg2) -> {
                string[0] = arg1;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs2Args, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs2Args 参数为 null 时应返回 null")
        void shouldReturnNull2ArgsWhenNull() {
            Funcs2Args<String, String, String> funcs2Args = Funcs2Args.delegate();
            String returnValue = invokeIfNotNull(funcs2Args, null, null);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs3Args 的结果")
        void shouldReturn3ArgsWhenNotNull() {
            String[] string = new String[1];
            Funcs3Args<String, String, String, String> funcs3Args = Funcs3Args.delegate();
            funcs3Args.add((arg1, arg2, arg3) -> {
                string[0] = arg1;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs3Args, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs3Args 参数为 null 时应返回 null")
        void shouldReturnNull3ArgsWhenNull() {
            Funcs3Args<String, String, String, String> funcs3Args = Funcs3Args.delegate();
            String returnValue = invokeIfNotNull(funcs3Args, null, null, null);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs4Args 的结果")
        void shouldReturn4ArgsWhenNotNull() {
            String[] string = new String[1];
            Funcs4Args<String, String, String, String, String> funcs4Args = Funcs4Args.delegate();
            funcs4Args.add((arg1, arg2, arg3, arg4) -> {
                string[0] = arg1;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs4Args, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs4Args 参数为 null 时应返回 null")
        void shouldReturnNull4ArgsWhenNull() {
            Funcs4Args<String, String, String, String, String> funcs4Args = Funcs4Args.delegate();
            String returnValue = invokeIfNotNull(funcs4Args, null, null, null, null);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs5Args 的结果")
        void shouldReturn5ArgsWhenNotNull() {
            String[] string = new String[1];
            Funcs5Args<String, String, String, String, String, String> funcs5Args = Funcs5Args.delegate();
            funcs5Args.add((arg1, arg2, arg3, arg4, arg5) -> {
                string[0] = arg1;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs5Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs5Args 参数为 null 时应返回 null")
        void shouldReturnNull5ArgsWhenNull() {
            Funcs5Args<String, String, String, String, String, String> funcs5Args = Funcs5Args.delegate();
            String returnValue = invokeIfNotNull(funcs5Args, null, null, null, null, null);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs6Args 的结果")
        void shouldReturn6ArgsWhenNotNull() {
            String[] string = new String[1];
            Funcs6Args<String, String, String, String, String, String, String> funcs6Args = Funcs6Args.delegate();
            funcs6Args.add((arg1, arg2, arg3, arg4, arg5, arg6) -> {
                string[0] = arg1;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs6Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs6Args 参数为 null 时应返回 null")
        void shouldReturnNull6ArgsWhenNull() {
            Funcs6Args<String, String, String, String, String, String, String> funcs6Args = Funcs6Args.delegate();
            String returnValue = invokeIfNotNull(funcs6Args, null, null, null, null, null, null);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs7Args 的结果")
        void shouldReturn7ArgsWhenNotNull() {
            String[] string = new String[1];
            Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = Funcs7Args.delegate();
            funcs7Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7) -> {
                string[0] = arg1;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs7Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs7Args 参数为 null 时应返回 null")
        void shouldReturnNull7ArgsWhenNull() {
            Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = Funcs7Args.delegate();
            String returnValue = invokeIfNotNull(funcs7Args, null, null, null, null, null, null, null);
            assertThat(returnValue).isNull();
        }

        @Test
        @DisplayName("应返回非空 Funcs8Args 的结果")
        void shouldReturn8ArgsWhenNotNull() {
            String[] string = new String[1];
            Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = Funcs8Args.delegate();
            funcs8Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> {
                string[0] = arg1;
                return defaultValue;
            });
            String returnValue = invokeIfNotNull(funcs8Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
            assertThat(string[0]).isEqualTo(defaultValue);
            assertThat(returnValue).isEqualTo(defaultValue);
        }

        @Test
        @DisplayName("Funcs8Args 参数为 null 时应返回 null")
        void shouldReturnNull8ArgsWhenNull() {
            Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = Funcs8Args.delegate();
            String returnValue = invokeIfNotNull(funcs8Args, null, null, null, null, null, null, null, null);
            assertThat(returnValue).isNull();
        }
    }
}
