package org.ryuu.functional;

@FunctionalInterface
public interface Action7Args<T1, T2, T3, T4, T5, T6, T7> extends Unicast {
    void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7);
}