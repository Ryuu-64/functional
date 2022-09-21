package org.ryuu.functional;

public final class Action3Arg<T1, T2, T3>
        extends Multicast<IAction3Arg<T1, T2, T3>>
        implements IAction3Arg<T1, T2, T3> {
    @Override
    public synchronized void invoke(T1 arg1, T2 arg2, T3 arg3) {
        for (IAction3Arg<T1, T2, T3> functional : this) {
            functional.invoke(arg1, arg2, arg3);
        }
    }
}