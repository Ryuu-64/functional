package org.ryuu.functional;

@FunctionalInterface
public interface Action1Arg<T> extends Unicast {
    void invoke(T arg);
}