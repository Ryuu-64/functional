package org.ryuu.functional;

@FunctionalInterface
public interface Func1Arg<T, TResult> extends Unicast {
    TResult invoke(T arg);
}