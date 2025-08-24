package org.ryuu.functional;

public final class Funcs<TResult>
        extends MulticastDelegate<Func<TResult>>
        implements Func<TResult> {
    private Funcs(boolean isEvent) {
        super(isEvent);
    }

    public static <TResult> Funcs<TResult> delegate() {
        return new Funcs<>(false);
    }

    public static <TResult> Funcs<TResult> event() {
        return new Funcs<>(true);
    }

    @Override
    public TResult invoke() {
        TResult result = null;
        for (Func<TResult> func : this) {
            result = func.invoke();
        }
        return result;
    }
}