package org.ryuu.functional.util;

import org.junit.jupiter.api.Test;
import org.ryuu.functional.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.ryuu.functional.util.FunctionalInvokeIfNotNullUtils.*;

class FunctionalInvokeIfNotNullUtilsTest {
    private final String defaultValue = "invokeNonNull";

    @Test
    void actionsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions actions = Actions.delegate();
        actions.add(() -> string[0] = defaultValue);
        invokeIfNotNull(actions);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actionsInvokeIfNotNullWithNull() {
        Actions actions = Actions.delegate();
        invokeIfNotNull(actions);
    }

    @Test
    void actions1ArgInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions1Arg<String> actions1Arg = Actions1Arg.delegate();
        actions1Arg.add(arg -> string[0] = arg);
        invokeIfNotNull(actions1Arg, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions1ArgInvokeIfNotNullWithNull() {
        Actions1Arg<String> actions1Arg = Actions1Arg.delegate();
        invokeIfNotNull(actions1Arg, null);
    }

    @Test
    void actions2ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions2Args<String, String> actions2Args = Actions2Args.delegate();
        actions2Args.add((arg1, arg2) -> string[0] = arg1);
        invokeIfNotNull(actions2Args, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions2ArgsInvokeIfNotNullWithNull() {
        Actions2Args<String, String> actions2Args = Actions2Args.delegate();
        invokeIfNotNull(actions2Args, null, null);
    }

    @Test
    void actions3ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions3Args<String, String, String> actions3Args = Actions3Args.delegate();
        actions3Args.add((arg1, arg2, arg3) -> string[0] = arg1);
        invokeIfNotNull(actions3Args, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions3ArgsInvokeIfNotNullWithNull() {
        Actions3Args<String, String, String> actions3Args = Actions3Args.delegate();
        invokeIfNotNull(actions3Args, null, null, null);
    }

    @Test
    void actions4ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions4Args<String, String, String, String> actions4Args = Actions4Args.delegate();
        actions4Args.add((arg1, arg2, arg3, arg4) -> string[0] = arg1);
        invokeIfNotNull(actions4Args, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions4ArgsInvokeIfNotNullWithNull() {
        Actions4Args<String, String, String, String> actions4Args = Actions4Args.delegate();
        invokeIfNotNull(actions4Args, null, null, null, null);
    }

    @Test
    void actions5ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions5Args<String, String, String, String, String> actions5Args = Actions5Args.delegate();
        actions5Args.add((arg1, arg2, arg3, arg4, arg5) -> string[0] = arg1);
        invokeIfNotNull(actions5Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions5ArgsInvokeIfNotNullWithNull() {
        Actions5Args<String, String, String, String, String> actions5Args = Actions5Args.delegate();
        invokeIfNotNull(actions5Args, null, null, null, null, null);
    }

    @Test
    void actions6ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions6Args<String, String, String, String, String, String> actions6Args = Actions6Args.delegate();
        actions6Args.add((arg1, arg2, arg3, arg4, arg5, arg6) -> string[0] = arg1);
        invokeIfNotNull(actions6Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions6ArgsInvokeIfNotNullWithNull() {
        Actions6Args<String, String, String, String, String, String> actions6Args = Actions6Args.delegate();
        invokeIfNotNull(actions6Args, null, null, null, null, null, null);
    }

    @Test
    void actions7ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions7Args<String, String, String, String, String, String, String> actions7Args = Actions7Args.delegate();
        actions7Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7) -> string[0] = arg1);
        invokeIfNotNull(actions7Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions7ArgsInvokeIfNotNullWithNull() {
        Actions7Args<String, String, String, String, String, String, String> actions7Args = Actions7Args.delegate();
        invokeIfNotNull(actions7Args, null, null, null, null, null, null, null);
    }

    @Test
    void actions8ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Actions8Args<String, String, String, String, String, String, String, String> actions8Args = Actions8Args.delegate();
        actions8Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> string[0] = arg1);
        invokeIfNotNull(actions8Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
    }

    @Test
    void actions8ArgsInvokeIfNotNullWithNull() {
        Actions8Args<String, String, String, String, String, String, String, String> actions8Args = Actions8Args.delegate();
        invokeIfNotNull(actions8Args, null, null, null, null, null, null, null, null);
    }

    @Test
    void funcsInvokeIfNotNull() {
        Funcs<String> funcs = Funcs.delegate();
        funcs.add(() -> defaultValue);
        String returnValue = invokeIfNotNull(funcs);
        assertEquals(defaultValue, returnValue);
    }

    @SuppressWarnings("ConstantValue")
    @Test
    void funcsInvokeIfNotNullWithNull() {
        Funcs<String> funcs = null;
        String returnValue = invokeIfNotNull(funcs);
        assertNull(returnValue);
    }

    @Test
    void funcs1ArgInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs1Arg<String, String> funcs1Arg = Funcs1Arg.delegate();
        funcs1Arg.add(arg -> {
            string[0] = arg;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs1Arg, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs1ArgInvokeIfNotNullWithNull() {
        Funcs1Arg<String, String> funcs1Arg = Funcs1Arg.delegate();
        String returnValue = invokeIfNotNull(funcs1Arg, null);
        assertNull(returnValue);
    }

    @Test
    void funcs2ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs2Args<String, String, String> funcs2Args = Funcs2Args.delegate();
        funcs2Args.add((arg1, arg2) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs2Args, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs2ArgsInvokeIfNotNullWithNull() {
        Funcs2Args<String, String, String> funcs2Args = Funcs2Args.delegate();
        String returnValue = invokeIfNotNull(funcs2Args, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs3ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs3Args<String, String, String, String> funcs3Args = Funcs3Args.delegate();
        funcs3Args.add((arg1, arg2, arg3) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs3Args, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs3ArgsInvokeIfNotNullWithNull() {
        Funcs3Args<String, String, String, String> funcs3Args = Funcs3Args.delegate();
        String returnValue = invokeIfNotNull(funcs3Args, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs4ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs4Args<String, String, String, String, String> funcs4Args = Funcs4Args.delegate();
        funcs4Args.add((arg1, arg2, arg3, arg4) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs4Args, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs4ArgsInvokeIfNotNullWithNull() {
        Funcs4Args<String, String, String, String, String> funcs4Args = Funcs4Args.delegate();
        String returnValue = invokeIfNotNull(funcs4Args, null, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs5ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs5Args<String, String, String, String, String, String> funcs5Args = Funcs5Args.delegate();
        funcs5Args.add((arg1, arg2, arg3, arg4, arg5) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs5Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs5ArgsInvokeIfNotNullWithNull() {
        Funcs5Args<String, String, String, String, String, String> funcs5Args = Funcs5Args.delegate();
        String returnValue = invokeIfNotNull(funcs5Args, null, null, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs6ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs6Args<String, String, String, String, String, String, String> funcs6Args = Funcs6Args.delegate();
        funcs6Args.add((arg1, arg2, arg3, arg4, arg5, arg6) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs6Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs6ArgsInvokeIfNotNullWithNull() {
        Funcs6Args<String, String, String, String, String, String, String> funcs6Args = Funcs6Args.delegate();
        String returnValue = invokeIfNotNull(funcs6Args, null, null, null, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs7ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = Funcs7Args.delegate();
        funcs7Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs7Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs7ArgsInvokeIfNotNullWithNull() {
        Funcs7Args<String, String, String, String, String, String, String, String> funcs7Args = Funcs7Args.delegate();
        String returnValue = invokeIfNotNull(funcs7Args, null, null, null, null, null, null, null);
        assertNull(returnValue);
    }

    @Test
    void funcs8ArgsInvokeIfNotNull() {
        final String[] string = new String[1];
        Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = Funcs8Args.delegate();
        funcs8Args.add((arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> {
            string[0] = arg1;
            return defaultValue;
        });
        String returnValue = invokeIfNotNull(funcs8Args, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue);
        assertEquals(defaultValue, string[0]);
        assertEquals(defaultValue, returnValue);
    }

    @Test
    void funcs8ArgsInvokeIfNotNullWithNull() {
        Funcs8Args<String, String, String, String, String, String, String, String, String> funcs8Args = Funcs8Args.delegate();
        String returnValue = invokeIfNotNull(funcs8Args, null, null, null, null, null, null, null, null);
        assertNull(returnValue);
    }
}