package org.ryuu.functional;

public final class Actions1Arg<T>
        extends MulticastDelegate<Action1Arg<T>>
        implements Action1Arg<T> {
    private Actions1Arg(boolean isEvent) {
        super(isEvent);
    }

    public static <T> Actions1Arg<T> delegate() {
        return new Actions1Arg<>(false);
    }

    public static <T> Actions1Arg<T> event() {
        return new Actions1Arg<>(true);
    }

    @Override
    public void invoke(T arg) {
        for (Action1Arg<T> action1Arg : this) {
            action1Arg.invoke(arg);
        }
    }
}