package org.ryuu.functional;

@FunctionalInterface
public interface IAction extends Unicast {
    void invoke();
}