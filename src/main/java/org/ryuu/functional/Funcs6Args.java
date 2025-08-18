package org.ryuu.functional;

public final class Funcs6Args<T1, T2, T3, T4, T5, T6, TResult>
        extends Multicast<Func6Args<T1, T2, T3, T4, T5, T6, TResult>>
        implements Func6Args<T1, T2, T3, T4, T5, T6, TResult> {
    @Override
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        TResult result = null;
        for (Func6Args<T1, T2, T3, T4, T5, T6, TResult> func6Args : this) {
            result = func6Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }
        return result;
    }
}