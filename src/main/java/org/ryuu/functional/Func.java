package org.ryuu.functional;

@FunctionalInterface
public interface Func<TResult> extends Delegate {
    TResult invoke();
}