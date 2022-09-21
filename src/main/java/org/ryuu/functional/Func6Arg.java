package org.ryuu.functional;

public final class Func6Arg<T1, T2, T3, T4, T5, T6, TResult>
        extends Multicast<IFunc6Arg<T1, T2, T3, T4, T5, T6, TResult>>
        implements IFunc6Arg<T1, T2, T3, T4, T5, T6, TResult> {
    @Override
    public synchronized TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        TResult result = null;
        for (IFunc6Arg<T1, T2, T3, T4, T5, T6, TResult> functional : this) {
            result = functional.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }
        return result;
    }
}