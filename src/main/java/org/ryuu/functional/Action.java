package org.ryuu.functional;

public final class Action
        extends Multicast<IAction>
        implements IAction {
    @Override
    public synchronized void invoke() {
        for (IAction functional : this) {
            functional.invoke();
        }
    }
}