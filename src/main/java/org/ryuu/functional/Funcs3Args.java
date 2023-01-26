package org.ryuu.functional;

public final class Funcs3Args<T1, T2, T3, TResult>
        extends Multicast<Func3Args<T1, T2, T3, TResult>>
        implements Func3Args<T1, T2, T3, TResult> {
    @Override
    public synchronized TResult invoke(T1 arg1, T2 arg2, T3 arg3) {
        TResult result = null;
        for (Func3Args<T1, T2, T3, TResult> func3Args : this) {
            result = func3Args.invoke(arg1, arg2, arg3);
        }
        return result;
    }
}