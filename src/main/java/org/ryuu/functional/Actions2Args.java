package org.ryuu.functional;

public final class Actions2Args<T1, T2>
        extends Multicast<Action2Args<T1, T2>>
        implements Action2Args<T1, T2> {
    @Override
    public synchronized void invoke(T1 arg1, T2 arg2) {
        for (Action2Args<T1, T2> action2Args : this) {
            action2Args.invoke(arg1, arg2);
        }
    }
}