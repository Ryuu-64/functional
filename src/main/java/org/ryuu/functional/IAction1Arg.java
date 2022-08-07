package org.ryuu.functional;

@FunctionalInterface
public interface IAction1Arg<T> extends Unicast {
    void invoke(T arg);
}
