package org.ryuu.functional;

public final class Actions3Args<T1, T2, T3>
        extends Multicast<Action3Args<T1, T2, T3>>
        implements Action3Args<T1, T2, T3> {
    @Override
    public void invoke(T1 arg1, T2 arg2, T3 arg3) {
        for (Action3Args<T1, T2, T3> action3Args : this) {
            action3Args.invoke(arg1, arg2, arg3);
        }
    }
}