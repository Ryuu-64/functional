package org.ryuu.functional.util;

import org.junit.jupiter.api.Test;
import org.ryuu.functional.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalUtilsTest {
    private final String defaultValue = "invokeNonNull";

    @Test
    void actionsInvokeNonNull() {
        final String[] string = new String[1];
        Actions actions = new Actions();
        actions.add(() -> string[0] = defaultValue);
        FunctionalUtils.invokeNonNull(actions);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actionsInvokeNonNullWithNull() {
        Actions actions = new Actions();
        FunctionalUtils.invokeNonNull(actions);
    }

    @Test
    void actions1ArgInvokeNonNull() {
        final String[] string = new String[1];
        Actions1Arg<String> actions1Arg = new Actions1Arg<>();
        actions1Arg.add(arg -> string[0] = arg);
        FunctionalUtils.invokeNonNull(actions1Arg, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions1ArgInvokeNonNullWithNull() {
        Actions1Arg<String> actions1Arg = new Actions1Arg<>();
        FunctionalUtils.invokeNonNull(actions1Arg, null);
    }

    @Test
    void actions2ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions2Args<String, String> actions2Args = new Actions2Args<>();
        actions2Args.add((arg1, arg2) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions2Args, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions2ArgsInvokeNonNullWithNull() {
        Actions2Args<String, String> actions2Args = new Actions2Args<>();
        FunctionalUtils.invokeNonNull(actions2Args, null, null);
    }

    @Test
    void actions3ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions3Args<String, String, String> actions3Args = new Actions3Args<>();
        actions3Args.add((arg1, arg2, arg3) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions3Args, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions3ArgsInvokeNonNullWithNull() {
        Actions3Args<String, String, String> actions3Args = new Actions3Args<>();
        FunctionalUtils.invokeNonNull(actions3Args, null, null, null);
    }

    @Test
    void actions4ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions4Args<String, String, String, String> actions4Args = new Actions4Args<>();
        actions4Args.add((arg1, arg2, arg3, arg4) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions4Args, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions4ArgsInvokeNonNullWithNull() {
        Actions4Args<String, String, String, String> actions4Args = new Actions4Args<>();
        FunctionalUtils.invokeNonNull(actions4Args, null, null, null, null);
    }

    @Test
    void actions5ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions5Args<String, String, String, String, String> actions5Args = new Actions5Args<>();
        actions5Args.add((arg1, arg2, arg3, arg4, arg5) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions5Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions5ArgsInvokeNonNullWithNull() {
        Actions5Args<String, String, String, String, String> actions5Args = new Actions5Args<>();
        FunctionalUtils.invokeNonNull(
                actions5Args,
                null, null, null, null, null
        );
    }

    @Test
    void actions6ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions6Args<String, String, String, String, String, String> actions6Args = new Actions6Args<>();
        actions6Args.add((arg1, arg2, arg3, arg4, arg5, arg6) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions6Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions6ArgsInvokeNonNullWithNull() {
        Actions6Args<String, String, String, String, String, String> actions6Args = new Actions6Args<>();
        FunctionalUtils.invokeNonNull(
                actions6Args,
                null, null, null, null, null, null
        );
    }

    @Test
    void actions7ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions7Args<String, String, String, String, String, String, String> actions7Args = new Actions7Args<>();
        actions7Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions7Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions7ArgsInvokeNonNullWithNull() {
        Actions7Args<String, String, String, String, String, String, String> actions7Args = new Actions7Args<>();
        FunctionalUtils.invokeNonNull(
                actions7Args,
                null, null, null, null, null, null, null
        );
    }

    @Test
    void actions8ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions8Args<String, String, String, String, String, String, String, String> actions8Args = new Actions8Args<>();
        actions8Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions8Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions8ArgsInvokeNonNullWithNull() {
        Actions8Args<String, String, String, String, String, String, String, String> actions8Args = new Actions8Args<>();
        FunctionalUtils.invokeNonNull(
                actions8Args,
                null, null, null, null, null, null, null, null
        );
    }

    @Test
    void funcsInvokeNonNull() {
        Funcs<String> funcs = new Funcs<>();
        funcs.add(() -> defaultValue);
        String returnValue = FunctionalUtils.invokeNonNull(funcs);
        assertEquals(defaultValue, returnValue);
    }

    @SuppressWarnings("ConstantValue")
    @Test
    void funcsInvokeNonNullWithNull() {
        Funcs<String> funcs = null;
        String returnValue = FunctionalUtils.invokeNonNull(funcs);
        assertNull(returnValue);
    }

    @Test
    void funcs1ArgInvokeNonNull() {
        final String[] string = new String[1];
        Funcs1Arg<String, String> funcs1Arg = new Funcs1Arg<>();
        funcs1Arg.add(arg -> {
            string[0] = arg;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(funcs1Arg, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs1ArgInvokeNonNullWithNull() {
        Funcs1Arg<String, String> funcs1Arg = new Funcs1Arg<>();
        String returnValue = FunctionalUtils.invokeNonNull(funcs1Arg, null);
        assertNull(returnValue);
    }

    @Test
    void funcs2ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs2Args<String, String, String> funcs2Args = new Funcs2Args<>();
        funcs2Args.add((arg1, arg2) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(funcs2Args, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs2ArgsInvokeNonNullWithNull() {
        Funcs2Args<String, String, String> funcs2Args = new Funcs2Args<>();
        String returnValue = FunctionalUtils.invokeNonNull(funcs2Args, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs3ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs3Args<String, String, String, String> funcs3Args = new Funcs3Args<>();
        funcs3Args.add((arg1, arg2, arg3) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs3Args, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs3ArgsInvokeNonNullWithNull() {
        Funcs3Args<String, String, String, String> funcs3Args = new Funcs3Args<>();
        String returnValue = FunctionalUtils.invokeNonNull(funcs3Args, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs4ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs4Args<String, String, String, String, String> funcs4Args = new Funcs4Args<>();
        funcs4Args.add((arg1, arg2, arg3, arg4) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs4Args, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs4ArgsInvokeNonNullWithNull() {
        Funcs4Args<String, String, String, String, String> funcs4Args = new Funcs4Args<>();
        String returnValue = FunctionalUtils.invokeNonNull(funcs4Args, null, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs5ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs5Args<String, String, String, String, String, String> funcs5Args = new Funcs5Args<>();
        funcs5Args.add((arg1, arg2, arg3, arg4, arg5) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs5Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs5ArgsInvokeNonNullWithNull() {
        Funcs5Args<String, String, String, String, String, String> funcs5Args = new Funcs5Args<>();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs5Args,
                null, null, null, null, null
        );
        assertNull(returnValue);
    }

    @Test
    void funcs6ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs6Args<String, String, String, String, String, String, String> funcs6Args = new Funcs6Args<>();
        funcs6Args.add((arg1, arg2, arg3, arg4, arg5, arg6) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs6Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs6ArgsInvokeNonNullWithNull() {
        Funcs6Args<String, String, String, String, String, String, String> funcs6Args = new Funcs6Args<>();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs6Args,
                null, null, null, null, null, null
        );
        assertNull(returnValue);
    }

    @Test
    void funcs7ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = new Funcs7Args<>();
        funcs7Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs7Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs7ArgsInvokeNonNullWithNull() {
        Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = new Funcs7Args<>();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs7Args,
                null, null, null, null, null, null, null
        );
        assertNull(returnValue);
    }

    @Test
    void funcs8ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = new Funcs8Args<>();
        funcs8Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs8Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs8ArgsInvokeNonNullWithNull() {
        Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = new Funcs8Args<>();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs8Args,
                null, null, null, null, null, null, null, null
        );
        assertNull(returnValue);
    }
}