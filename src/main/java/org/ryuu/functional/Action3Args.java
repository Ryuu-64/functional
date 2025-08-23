package org.ryuu.functional;

@FunctionalInterface
public interface Action3Args<T1, T2, T3> extends Delegate {
    void invoke(T1 arg1, T2 arg2, T3 arg3);
}