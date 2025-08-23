package org.ryuu.functional;

@FunctionalInterface
public interface Action2Args<T1, T2> extends Delegate {
    void invoke(T1 arg1, T2 arg2);
}