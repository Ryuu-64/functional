package org.ryuu.functional;

public final class Funcs5Args<T1, T2, T3, T4, T5, TResult>
        extends MulticastDelegate<Func5Args<T1, T2, T3, T4, T5, TResult>>
        implements Func5Args<T1, T2, T3, T4, T5, TResult> {
    private Funcs5Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2, T3, T4, T5, TResult> Funcs5Args<T1, T2, T3, T4, T5, TResult> delegate() {
        return new Funcs5Args<>(false);
    }

    public static <T1, T2, T3, T4, T5, TResult> Funcs5Args<T1, T2, T3, T4, T5, TResult> event() {
        return new Funcs5Args<>(true);
    }

    @Override
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        TResult result = null;
        for (Func5Args<T1, T2, T3, T4, T5, TResult> func5Args : this) {
            result = func5Args.invoke(arg1, arg2, arg3, arg4, arg5);
        }
        return result;
    }
}