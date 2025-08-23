package org.ryuu.functional;

@FunctionalInterface
public interface Func4Args<T1, T2, T3, T4, TResult> extends Delegate {
    TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
}