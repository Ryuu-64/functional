package org.ryuu.functional.util;

import org.ryuu.functional.*;

public final class FunctionalInvokeIfNotNullUtils {
    private FunctionalInvokeIfNotNullUtils() {
    }

    public static void invokeIfNotNull(Action action) {
        if (action != null) {
            action.invoke();
        }
    }

    public static <T> void invokeIfNotNull(Action1Arg<T> action1Arg, T arg) {
        if (action1Arg != null) {
            action1Arg.invoke(arg);
        }
    }

    public static <T1, T2> void invokeIfNotNull(Action2Args<T1, T2> action2Args, T1 arg1, T2 arg2) {
        if (action2Args != null) {
            action2Args.invoke(arg1, arg2);
        }
    }

    public static <T1, T2, T3> void invokeIfNotNull(Action3Args<T1, T2, T3> action3Args, T1 arg1, T2 arg2, T3 arg3) {
        if (action3Args != null) {
            action3Args.invoke(arg1, arg2, arg3);
        }
    }

    public static <T1, T2, T3, T4> void invokeIfNotNull(Action4Args<T1, T2, T3, T4> action4Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        if (action4Args != null) {
            action4Args.invoke(arg1, arg2, arg3, arg4);
        }
    }

    public static <T1, T2, T3, T4, T5> void invokeIfNotNull(Action5Args<T1, T2, T3, T4, T5> action5Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        if (action5Args != null) {
            action5Args.invoke(arg1, arg2, arg3, arg4, arg5);
        }
    }

    public static <T1, T2, T3, T4, T5, T6> void invokeIfNotNull(Action6Args<T1, T2, T3, T4, T5, T6> action6Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        if (action6Args != null) {
            action6Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }
    }

    public static <T1, T2, T3, T4, T5, T6, T7> void invokeIfNotNull(Action7Args<T1, T2, T3, T4, T5, T6, T7> action7Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7) {
        if (action7Args != null) {
            action7Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> void invokeIfNotNull(Action8Args<T1, T2, T3, T4, T5, T6, T7, T8> action8Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8) {
        if (action8Args != null) {
            action8Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
    }

    public static <TResult> TResult invokeIfNotNull(Func<TResult> func) {
        if (func != null) {
            return func.invoke();
        }

        return null;
    }

    public static <T, TResult> TResult invokeIfNotNull(Func1Arg<T, TResult> func1Arg, T arg) {
        if (func1Arg != null) {
            return func1Arg.invoke(arg);
        }

        return null;
    }

    public static <T1, T2, TResult> TResult invokeIfNotNull(Func2Args<T1, T2, TResult> func2Args, T1 arg1, T2 arg2) {
        if (func2Args != null) {
            return func2Args.invoke(arg1, arg2);
        }

        return null;
    }

    public static <T1, T2, T3, TResult> TResult invokeIfNotNull(Func3Args<T1, T2, T3, TResult> func3Args, T1 arg1, T2 arg2, T3 arg3) {
        if (func3Args != null) {
            return func3Args.invoke(arg1, arg2, arg3);
        }

        return null;
    }

    public static <T1, T2, T3, T4, TResult> TResult invokeIfNotNull(Func4Args<T1, T2, T3, T4, TResult> func4Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        if (func4Args != null) {
            return func4Args.invoke(arg1, arg2, arg3, arg4);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, TResult> TResult invokeIfNotNull(Func5Args<T1, T2, T3, T4, T5, TResult> func5Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        if (func5Args != null) {
            return func5Args.invoke(arg1, arg2, arg3, arg4, arg5);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, T6, TResult> TResult invokeIfNotNull(Func6Args<T1, T2, T3, T4, T5, T6, TResult> func6Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        if (func6Args != null) {
            return func6Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, T6, T7, TResult> TResult invokeIfNotNull(Func7Args<T1, T2, T3, T4, T5, T6, T7, TResult> func7Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7) {
        if (func7Args != null) {
            return func7Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }

        return null;
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, TResult> TResult invokeIfNotNull(Func8Args<T1, T2, T3, T4, T5, T6, T7, T8, TResult> func8Args, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8) {
        if (func8Args != null) {
            return func8Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }

        return null;
    }
}
