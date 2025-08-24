package org.ryuu.functional;

public final class Actions7Args<T1, T2, T3, T4, T5, T6, T7>
        extends MulticastDelegate<Action7Args<T1, T2, T3, T4, T5, T6, T7>>
        implements Action7Args<T1, T2, T3, T4, T5, T6, T7> {
    private Actions7Args(boolean isEvent) {
        super(isEvent);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Actions7Args<T1, T2, T3, T4, T5, T6, T7> delegate() {
        return new Actions7Args<>(false);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Actions7Args<T1, T2, T3, T4, T5, T6, T7> event() {
        return new Actions7Args<>(true);
    }

    @Override
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7) {
        for (Action7Args<T1, T2, T3, T4, T5, T6, T7> action7Args : this) {
            action7Args.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
    }
}