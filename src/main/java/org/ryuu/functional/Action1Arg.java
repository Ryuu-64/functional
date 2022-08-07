package org.ryuu.functional;

public final class Action1Arg<T>
        extends Multicast<IAction1Arg<T>>
        implements IAction1Arg<T> {
    @Override
    public void invoke(T arg) {
        for (IAction1Arg<T> functional : this) {
            functional.invoke(arg);
        }
    }
}