package org.ryuu.functional.util;

import org.junit.jupiter.api.Test;
import org.ryuu.functional.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalUtilsTest {
    private final String defaultValue = "invokeNonNull";

    @Test
    void actionsInvokeNonNull() {
        final String[] string = new String[1];
        Actions actions = Actions.delegate();
        actions.add(() -> string[0] = defaultValue);
        FunctionalUtils.invokeNonNull(actions);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actionsInvokeNonNullWithNull() {
        Actions actions = Actions.delegate();
        FunctionalUtils.invokeNonNull(actions);
    }

    @Test
    void actions1ArgInvokeNonNull() {
        final String[] string = new String[1];
        Actions1Arg<String> actions1Arg = Actions1Arg.delegate();
        actions1Arg.add(arg -> string[0] = arg);
        FunctionalUtils.invokeNonNull(actions1Arg, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions1ArgInvokeNonNullWithNull() {
        Actions1Arg<String> actions1Arg = Actions1Arg.delegate();
        FunctionalUtils.invokeNonNull(actions1Arg, null);
    }

    @Test
    void actions2ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions2Args<String, String> actions2Args = Actions2Args.delegate();
        actions2Args.add((arg1, arg2) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions2Args, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions2ArgsInvokeNonNullWithNull() {
        Actions2Args<String, String> actions2Args = Actions2Args.delegate();
        FunctionalUtils.invokeNonNull(actions2Args, null, null);
    }

    @Test
    void actions3ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions3Args<String, String, String> actions3Args = Actions3Args.delegate();
        actions3Args.add((arg1, arg2, arg3) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions3Args, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions3ArgsInvokeNonNullWithNull() {
        Actions3Args<String, String, String> actions3Args = Actions3Args.delegate();
        FunctionalUtils.invokeNonNull(actions3Args, null, null, null);
    }

    @Test
    void actions4ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions4Args<String, String, String, String> actions4Args = Actions4Args.delegate();
        actions4Args.add((arg1, arg2, arg3, arg4) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions4Args, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions4ArgsInvokeNonNullWithNull() {
        Actions4Args<String, String, String, String> actions4Args = Actions4Args.delegate();
        FunctionalUtils.invokeNonNull(actions4Args, null, null, null, null);
    }

    @Test
    void actions5ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions5Args<String, String, String, String, String> actions5Args = Actions5Args.delegate();
        actions5Args.add((arg1, arg2, arg3, arg4, arg5) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions5Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions5ArgsInvokeNonNullWithNull() {
        Actions5Args<String, String, String, String, String> actions5Args = Actions5Args.delegate();
        FunctionalUtils.invokeNonNull(
                actions5Args,
                null, null, null, null, null
        );
    }

    @Test
    void actions6ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions6Args<String, String, String, String, String, String> actions6Args = Actions6Args.delegate();
        actions6Args.add((arg1, arg2, arg3, arg4, arg5, arg6) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions6Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions6ArgsInvokeNonNullWithNull() {
        Actions6Args<String, String, String, String, String, String> actions6Args = Actions6Args.delegate();
        FunctionalUtils.invokeNonNull(
                actions6Args,
                null, null, null, null, null, null
        );
    }

    @Test
    void actions7ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions7Args<String, String, String, String, String, String, String> actions7Args = Actions7Args.delegate();
        actions7Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions7Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions7ArgsInvokeNonNullWithNull() {
        Actions7Args<String, String, String, String, String, String, String> actions7Args = Actions7Args.delegate();
        FunctionalUtils.invokeNonNull(
                actions7Args,
                null, null, null, null, null, null, null
        );
    }

    @Test
    void actions8ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Actions8Args<String, String, String, String, String, String, String, String> actions8Args = Actions8Args.delegate();
        actions8Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> string[0] = arg1);
        FunctionalUtils.invokeNonNull(
                actions8Args,
                defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue
        );
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions8ArgsInvokeNonNullWithNull() {
        Actions8Args<String, String, String, String, String, String, String, String> actions8Args = Actions8Args.delegate();
        FunctionalUtils.invokeNonNull(
                actions8Args,
                null, null, null, null, null, null, null, null
        );
    }

    @Test
    void funcsInvokeNonNull() {
        Funcs<String> funcs = Funcs.delegate();
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
        Funcs1Arg<String, String> funcs1Arg = Funcs1Arg.delegate();
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
        Funcs1Arg<String, String> funcs1Arg = Funcs1Arg.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(funcs1Arg, null);
        assertNull(returnValue);
    }

    @Test
    void funcs2ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs2Args<String, String, String> funcs2Args = Funcs2Args.delegate();
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
        Funcs2Args<String, String, String> funcs2Args = Funcs2Args.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(funcs2Args, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs3ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs3Args<String, String, String, String> funcs3Args = Funcs3Args.delegate();
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
        Funcs3Args<String, String, String, String> funcs3Args = Funcs3Args.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(funcs3Args, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs4ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs4Args<String, String, String, String, String> funcs4Args = Funcs4Args.delegate();
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
        Funcs4Args<String, String, String, String, String> funcs4Args = Funcs4Args.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(funcs4Args, null, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs5ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs5Args<String, String, String, String, String, String> funcs5Args = Funcs5Args.delegate();
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
        Funcs5Args<String, String, String, String, String, String> funcs5Args = Funcs5Args.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs5Args,
                null, null, null, null, null
        );
        assertNull(returnValue);
    }

    @Test
    void funcs6ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs6Args<String, String, String, String, String, String, String> funcs6Args = Funcs6Args.delegate();
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
        Funcs6Args<String, String, String, String, String, String, String> funcs6Args = Funcs6Args.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs6Args,
                null, null, null, null, null, null
        );
        assertNull(returnValue);
    }

    @Test
    void funcs7ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = Funcs7Args.delegate();
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
        Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = Funcs7Args.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs7Args,
                null, null, null, null, null, null, null
        );
        assertNull(returnValue);
    }

    @Test
    void funcs8ArgsInvokeNonNull() {
        final String[] string = new String[1];
        Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = Funcs8Args.delegate();
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
        Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = Funcs8Args.delegate();
        String returnValue = FunctionalUtils.invokeNonNull(
                funcs8Args,
                null, null, null, null, null, null, null, null
        );
        assertNull(returnValue);
    }
}