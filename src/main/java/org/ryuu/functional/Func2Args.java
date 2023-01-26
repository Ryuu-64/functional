package org.ryuu.functional;

@FunctionalInterface
public interface Func2Args<T1, T2, TResult> extends Unicast {
    TResult invoke(T1 arg1, T2 arg2);
}