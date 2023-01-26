package org.ryuu.functional;

public final class Actions8Args<T1, T2, T3, T4, T5, T6, T7, T8>
        extends Multicast<Action8Args<T1, T2, T3, T4, T5, T6, T7, T8>>
        implements Action8Args<T1, T2, T3, T4, T5, T6, T7, T8> {
    @Override
    public synchronized void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8) {
        for (Action8Args<T1, T2, T3, T4, T5, T6, T7, T8> action8Args : this) {
            action8Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
    }
}