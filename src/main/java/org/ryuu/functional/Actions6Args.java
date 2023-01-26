package org.ryuu.functional;

public final class Actions6Args<T1, T2, T3, T4, T5, T6>
        extends Multicast<Action6Args<T1, T2, T3, T4, T5, T6>>
        implements Action6Args<T1, T2, T3, T4, T5, T6> {
    @Override
    public synchronized void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        for (Action6Args<T1, T2, T3, T4, T5, T6> action6Args : this) {
            action6Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }
    }
}