package org.ryuu.functional;

public final class Func5Arg<T1, T2, T3, T4, T5, TResult>
        extends Multicast<IFunc5Arg<T1, T2, T3, T4, T5, TResult>>
        implements IFunc5Arg<T1, T2, T3, T4, T5, TResult> {
    @Override
    public synchronized TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        TResult result = null;
        for (IFunc5Arg<T1, T2, T3, T4, T5, TResult> functional : this) {
            result = functional.invoke(arg1, arg2, arg3, arg4, arg5);
        }
        return result;
    }
}