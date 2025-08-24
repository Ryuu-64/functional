package org.ryuu.functional;

public final class Actions6Args<T1, T2, T3, T4, T5, T6>
        extends MulticastDelegate<Action6Args<T1, T2, T3, T4, T5, T6>>
        implements Action6Args<T1, T2, T3, T4, T5, T6> {
    private Actions6Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2, T3, T4, T5, T6> Actions6Args<T1, T2, T3, T4, T5, T6> delegate() {
        return new Actions6Args<>(false);
    }

    public static <T1, T2, T3, T4, T5, T6> Actions6Args<T1, T2, T3, T4, T5, T6> event() {
        return new Actions6Args<>(true);
    }

    @Override
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        for (Action6Args<T1, T2, T3, T4, T5, T6> action6Args : this) {
            action6Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }
    }
}