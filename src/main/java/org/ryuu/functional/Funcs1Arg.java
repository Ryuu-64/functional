package org.ryuu.functional;

public final class Funcs1Arg<T, TResult>
        extends MulticastDelegate<Func1Arg<T, TResult>>
        implements Func1Arg<T, TResult> {
    @Override
    public TResult invoke(T arg) {
        TResult result = null;
        for (Func1Arg<T, TResult> func1Arg : this) {
            result = func1Arg.invoke(arg);
        }
        return result;
    }
}