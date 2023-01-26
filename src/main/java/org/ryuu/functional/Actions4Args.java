package org.ryuu.functional;

public final class Actions4Args<T1, T2, T3, T4>
        extends Multicast<Action4Args<T1, T2, T3, T4>>
        implements Action4Args<T1, T2, T3, T4> {
    @Override
    public synchronized void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        for (Action4Args<T1, T2, T3, T4> action4Args : this) {
            action4Args.invoke(arg1, arg2, arg3, arg4);
        }
    }
}