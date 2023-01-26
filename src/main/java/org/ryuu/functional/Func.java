package org.ryuu.functional;

@FunctionalInterface
public interface Func<TResult> extends Unicast {
    TResult invoke();
}