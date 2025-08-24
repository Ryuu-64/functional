package org.ryuu.functional;

public final class Funcs3Args<T1, T2, T3, TResult>
        extends MulticastDelegate<Func3Args<T1, T2, T3, TResult>>
        implements Func3Args<T1, T2, T3, TResult> {
    private Funcs3Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2, T3, TResult> Funcs3Args<T1, T2, T3, TResult> delegate() {
        return new Funcs3Args<>(false);
    }

    public static <T1, T2, T3, TResult> Funcs3Args<T1, T2, T3, TResult> event() {
        return new Funcs3Args<>(true);
    }

    @Override
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3) {
        TResult result = null;
        for (Func3Args<T1, T2, T3, TResult> func3Args : this) {
            result = func3Args.invoke(arg1, arg2, arg3);
        }
        return result;
    }
}