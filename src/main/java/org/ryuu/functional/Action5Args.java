package org.ryuu.functional;

@FunctionalInterface
public interface Action5Args<T1, T2, T3, T4, T5> extends Delegate {
    void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
}