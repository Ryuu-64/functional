package org.ryuu.functional;

@FunctionalInterface
public interface IAction4Arg<T1, T2, T3, T4> extends Unicast {
    void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
}