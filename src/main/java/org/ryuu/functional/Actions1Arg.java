package org.ryuu.functional;

public final class Actions1Arg<T>
        extends MulticastDelegate<Action1Arg<T>>
        implements Action1Arg<T> {
    @Override
    public void invoke(T arg) {
        for (Action1Arg<T> action1Arg : this) {
            action1Arg.invoke(arg);
        }
    }
}