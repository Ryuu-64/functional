package org.ryuu.functional;

public final class Func3Arg<T1, T2, T3, TResult>
        extends Multicast<IFunc3Arg<T1, T2, T3, TResult>>
        implements IFunc3Arg<T1, T2, T3, TResult> {
    @Override
    public synchronized TResult invoke(T1 arg1, T2 arg2, T3 arg3) {
        TResult result = null;
        for (IFunc3Arg<T1, T2, T3, TResult> functional : this) {
            result = functional.invoke(arg1, arg2, arg3);
        }
        return result;
    }
}