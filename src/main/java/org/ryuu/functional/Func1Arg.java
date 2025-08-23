package org.ryuu.functional;

@FunctionalInterface
public interface Func1Arg<T, TResult> extends Delegate {
    TResult invoke(T arg);
}