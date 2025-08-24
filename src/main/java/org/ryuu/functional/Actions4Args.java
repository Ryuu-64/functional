package org.ryuu.functional;

public final class Actions4Args<T1, T2, T3, T4>
        extends MulticastDelegate<Action4Args<T1, T2, T3, T4>>
        implements Action4Args<T1, T2, T3, T4> {
    private Actions4Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2, T3, T4> Actions4Args<T1, T2, T3, T4> delegate() {
        return new Actions4Args<>(false);
    }

    public static <T1, T2, T3, T4> Actions4Args<T1, T2, T3, T4> event() {
        return new Actions4Args<>(true);
    }

    @Override
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        for (Action4Args<T1, T2, T3, T4> action4Args : this) {
            action4Args.invoke(arg1, arg2, arg3, arg4);
        }
    }
}