package org.ryuu.functional;

public final class Actions
        extends Multicast<Action>
        implements Action {
    @Override
    public void invoke() {
        for (Action action : this) {
            action.invoke();
        }
    }
}