package org.ryuu.functional;

public final class Actions1Arg<T>
        extends Multicast<Action1Arg<T>>
        implements Action1Arg<T> {
    @Override
    public synchronized void invoke(T arg) {
        for (Action1Arg<T> action1Arg : this) {
            action1Arg.invoke(arg);
        }
    }
}