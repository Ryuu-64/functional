package org.ryuu.functional;

public final class Action
        extends Multicast<IAction>
        implements IAction {
    @Override
    public void invoke() {
        for (IAction functional : this) {
            functional.invoke();
        }
    }
}