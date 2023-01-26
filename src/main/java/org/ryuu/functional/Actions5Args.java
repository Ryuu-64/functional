package org.ryuu.functional;

public final class Actions5Args<T1, T2, T3, T4, T5>
        extends Multicast<Action5Args<T1, T2, T3, T4, T5>>
        implements Action5Args<T1, T2, T3, T4, T5> {
    @Override
    public synchronized void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        for (Action5Args<T1, T2, T3, T4, T5> action5Args : this) {
            action5Args.invoke(arg1, arg2, arg3, arg4, arg5);
        }
    }
}