package org.ryuu.functional;

@FunctionalInterface
public interface Action extends Delegate {
    void invoke();
}