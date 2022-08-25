package org.ryuu.functional;

public final class Func1Arg<T, TResult>
        extends Multicast<IFunc1Arg<T, TResult>>
        implements IFunc1Arg<T, TResult> {
    @Override
    public TResult invoke(T arg) {
        TResult result = null;
        for (IFunc1Arg<T, TResult> functional : this) {
            result = functional.invoke(arg);
        }
        return result;
    }
}