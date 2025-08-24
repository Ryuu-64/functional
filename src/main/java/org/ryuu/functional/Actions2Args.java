package org.ryuu.functional;

public final class Actions2Args<T1, T2>
        extends MulticastDelegate<Action2Args<T1, T2>>
        implements Action2Args<T1, T2> {
    private Actions2Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2> Actions2Args<T1, T2> delegate() {
        return new Actions2Args<>(false);
    }

    public static <T1, T2> Actions2Args<T1, T2> event() {
        return new Actions2Args<>(true);
    }

    @Override
    public void invoke(T1 arg1, T2 arg2) {
        for (Action2Args<T1, T2> action2Args : this) {
            action2Args.invoke(arg1, arg2);
        }
    }
}