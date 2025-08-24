package org.ryuu.functional;

public final class Funcs2Args<T1, T2, TResult>
        extends MulticastDelegate<Func2Args<T1, T2, TResult>>
        implements Func2Args<T1, T2, TResult> {
    private Funcs2Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2, TResult> Funcs2Args<T1, T2, TResult> delegate() {
        return new Funcs2Args<>(false);
    }

    public static <T1, T2, TResult> Funcs2Args<T1, T2, TResult> event() {
        return new Funcs2Args<>(true);
    }

    @Override
    public TResult invoke(T1 arg1, T2 arg2) {
        TResult result = null;
        for (Func2Args<T1, T2, TResult> func2Args : this) {
            result = func2Args.invoke(arg1, arg2);
        }
        return result;
    }
}