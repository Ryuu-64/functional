package org.ryuu.functional;

public final class Actions7Args<T1, T2, T3, T4, T5, T6, T7>
        extends Multicast<Action7Args<T1, T2, T3, T4, T5, T6, T7>>
        implements Action7Args<T1, T2, T3, T4, T5, T6, T7> {
    @Override
    public synchronized void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7) {
        for (Action7Args<T1, T2, T3, T4, T5, T6, T7> action7Args : this) {
            action7Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
    }
}