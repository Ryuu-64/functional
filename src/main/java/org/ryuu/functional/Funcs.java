package org.ryuu.functional;

public final class Funcs<TResult>
        extends MulticastDelegate<Func<TResult>>
        implements Func<TResult> {
    @Override
    public TResult invoke() {
        TResult result = null;
        for (Func<TResult> func : this) {
            result = func.invoke();
        }
        return result;
    }
}