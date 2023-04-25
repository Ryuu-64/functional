package org.ryuu.functional.util;

import org.junit.jupiter.api.Test;
import org.ryuu.functional.Actions;
import org.ryuu.functional.Actions1Arg;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalUtilsTest {
    @Test
    void actionsInvokeNonNull() {
        final String[] string = new String[1];
        Actions actions = new Actions();
        actions.add(() -> string[0] = "invokeNonNull");
        FunctionalUtils.invokeNonNull(actions);
        assertEquals("invokeNonNull", string[0]);
    }

    @Test
    void actions1ArgInvokeNonNull() {
        final String[] string = new String[1];
        Actions1Arg<String> actions1Arg = new Actions1Arg<>();
        actions1Arg.add(arg -> string[0] = arg);
        FunctionalUtils.invokeNonNull(actions1Arg, "invokeNonNull");
        assertEquals("invokeNonNull", string[0]);
    }
}