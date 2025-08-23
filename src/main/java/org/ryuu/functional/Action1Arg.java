package org.ryuu.functional;

@FunctionalInterface
public interface Action1Arg<T> extends Delegate {
    void invoke(T arg);
}