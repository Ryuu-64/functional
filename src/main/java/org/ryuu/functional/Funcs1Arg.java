package org.ryuu.functional;

public final class Funcs1Arg<T, TResult>
        extends MulticastDelegate<Func1Arg<T, TResult>>
        implements Func1Arg<T, TResult> {
    private Funcs1Arg(boolean isEvent) {
        super(isEvent);
    }

    public static <T, TResult> Funcs1Arg<T, TResult> delegate() {
        return new Funcs1Arg<>(false);
    }

    public static <T, TResult> Funcs1Arg<T, TResult> event() {
        return new Funcs1Arg<>(true);
    }

    @Override
    public TResult invoke(T arg) {
        TResult result = null;
        for (Func1Arg<T, TResult> func1Arg : this) {
            result = func1Arg.invoke(arg);
        }
        return result;
    }
}