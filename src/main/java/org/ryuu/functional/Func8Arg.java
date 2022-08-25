package org.ryuu.functional;

public final class Func8Arg<T1, T2, T3, T4, T5, T6, T7, T8, TResult>
        extends Multicast<IFunc8Arg<T1, T2, T3, T4, T5, T6, T7, T8, TResult>>
        implements IFunc8Arg<T1, T2, T3, T4, T5, T6, T7, T8, TResult> {
    @Override
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8) {
        TResult result = null;
        for (IFunc8Arg<T1, T2, T3, T4, T5, T6, T7, T8, TResult> functional : this) {
            result = functional.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
        return result;
    }
}