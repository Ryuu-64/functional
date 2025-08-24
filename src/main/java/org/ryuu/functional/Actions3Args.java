package org.ryuu.functional;

public final class Actions3Args<T1, T2, T3>
        extends MulticastDelegate<Action3Args<T1, T2, T3>>
        implements Action3Args<T1, T2, T3> {
    private Actions3Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2, T36> Actions3Args<T1, T2, T36> delegate() {
        return new Actions3Args<>(false);
    }

    public static <T1, T2, T36> Actions3Args<T1, T2, T36> event() {
        return new Actions3Args<>(true);
    }

    @Override
    public void invoke(T1 arg1, T2 arg2, T3 arg3) {
        for (Action3Args<T1, T2, T3> action3Args : this) {
            action3Args.invoke(arg1, arg2, arg3);
        }
    }
}