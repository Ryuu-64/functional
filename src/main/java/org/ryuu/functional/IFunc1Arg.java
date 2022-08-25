package org.ryuu.functional;

@FunctionalInterface
public interface IFunc1Arg<T, TResult> extends Unicast {
    TResult invoke(T arg);
}