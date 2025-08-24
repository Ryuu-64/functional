package org.ryuu.functional;

public interface Event<T extends Delegate> {
    void add(T delegate);

    void remove(T delegate);
}