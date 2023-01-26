package org.ryuu.functional;

@FunctionalInterface
public interface Func3Args<T1, T2, T3, TResult> extends Unicast {
    TResult invoke(T1 arg1, T2 arg2, T3 arg3);
}