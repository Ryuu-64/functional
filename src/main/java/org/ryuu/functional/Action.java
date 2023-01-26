package org.ryuu.functional;

@FunctionalInterface
public interface Action extends Unicast {
    void invoke();
}