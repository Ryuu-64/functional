package org.ryuu.functional;

public final class Funcs4Args<T1, T2, T3, T4, TResult>
        extends MulticastDelegate<Func4Args<T1, T2, T3, T4, TResult>>
        implements Func4Args<T1, T2, T3, T4, TResult> {
    @Override
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        TResult result = null;
        for (Func4Args<T1, T2, T3, T4, TResult> func4Args : this) {
            result = func4Args.invoke(arg1, arg2, arg3, arg4);
        }
        return result;
    }
}