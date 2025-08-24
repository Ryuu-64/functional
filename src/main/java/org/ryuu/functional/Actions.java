package org.ryuu.functional;

public final class Actions
        extends MulticastDelegate<Action>
        implements Action {
    private Actions(boolean isEvent) {
        super(isEvent);
    }

    public static Actions delegate() {
        return new Actions(false);
    }

    public static Actions event() {
        return new Actions(true);
    }

    @Override
    public void invoke() {
        for (Action action : this) {
            action.invoke();
        }
    }
}
