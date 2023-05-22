package org.ryuu.functional.util;

import org.ryuu.functional.*;

public class FunctionalUtils {
    private FunctionalUtils() {
    }

    public static void invokeNonNull(Action action) {
        if (action != null) {
            action.invoke();
        }
    }

    public static <T> void invokeNonNull(Action1Arg<T> action1Arg, T arg) {
        if (action1Arg != null) {
            action1Arg.invoke(arg);
        }
    }

    public static <T1, T2> void invokeNonNull(Action2Args<T1, T2> action2Args, T1 arg1, T2 arg2) {
        if (action2Args != null) {
            action2Args.invoke(arg1, arg2);
        }
    }

    public static <T1, T2, T3> void invokeNonNull(
            Action3Args<T1, T2, T3> action3Args,
            T1 arg1, T2 arg2, T3 arg3
    ) {
        if (action3Args != null) {
            action3Args.invoke(arg1, arg2, arg3);
        }
    }

    public static <T1, T2, T3, T4> void invokeNonNull(
            Action4Args<T1, T2, T3, T4> action4Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4
    ) {
        if (action4Args != null) {
            action4Args.invoke(arg1, arg2, arg3, arg4);
        }
    }

    public static <T1, T2, T3, T4, T5> void invokeNonNull(
            Action5Args<T1, T2, T3, T4, T5> action5Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5
    ) {
        if (action5Args != null) {
            action5Args.invoke(arg1, arg2, arg3, arg4, arg5);
        }
    }

    public static <T1, T2, T3, T4, T5, T6> void invokeNonNull(
            Action6Args<T1, T2, T3, T4, T5, T6> action6Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6
    ) {
        if (action6Args != null) {
            action6Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }
    }

    public static <T1, T2, T3, T4, T5, T6, T7> void invokeNonNull(
            Action7Args<T1, T2, T3, T4, T5, T6, T7> action7Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7
    ) {
        if (action7Args != null) {
            action7Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> void invokeNonNull(
            Action8Args<T1, T2, T3, T4, T5, T6, T7, T8> action8Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8
    ) {
        if (action8Args != null) {
            action8Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
    }

    public static <TResult> TResult invokeNonNull(Func<TResult> func) {
        if (func != null) {
            return func.invoke();
        }

        return null;
    }

    public static <T, TResult> TResult invokeNonNull(Func1Arg<T, TResult> func1Arg, T arg) {
        if (func1Arg != null) {
            return func1Arg.invoke(arg);
        }

        return null;
    }

    public static <T1, T2, TResult> TResult invokeNonNull(
            Func2Args<T1, T2, TResult> func2Args,
            T1 arg1, T2 arg2
    ) {
        if (func2Args != null) {
            return func2Args.invoke(arg1, arg2);
        }

        return null;
    }

    public static <T1, T2, T3, TResult> TResult invokeNonNull(
            Func3Args<T1, T2, T3, TResult> func3Args,
            T1 arg1, T2 arg2, T3 arg3
    ) {
        if (func3Args != null) {
            return func3Args.invoke(arg1, arg2, arg3);
        }

        return null;
    }

    public static <T1, T2, T3, T4, TResult> TResult invokeNonNull(
            Func4Args<T1, T2, T3, T4, TResult> func4Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4
    ) {
        if (func4Args != null) {
            return func4Args.invoke(arg1, arg2, arg3, arg4);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, TResult> TResult invokeNonNull(
            Func5Args<T1, T2, T3, T4, T5, TResult> func5Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5
    ) {
        if (func5Args != null) {
            return func5Args.invoke(arg1, arg2, arg3, arg4, arg5);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, T6, TResult> TResult invokeNonNull(
            Func6Args<T1, T2, T3, T4, T5, T6, TResult> func6Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6
    ) {
        if (func6Args != null) {
            return func6Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, T6, T7, TResult> TResult invokeNonNull(
            Func7Args<T1, T2, T3, T4, T5, T6, T7, TResult> func7Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7
    ) {
        if (func7Args != null) {
            return func7Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, TResult> TResult invokeNonNull(
            Func8Args<T1, T2, T3, T4, T5, T6, T7, T8, TResult> func8Args,
            T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8
    ) {
        if (func8Args != null) {
            return func8Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }

        return null;
    }
}
