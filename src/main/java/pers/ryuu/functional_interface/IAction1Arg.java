package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IAction1Arg<T> extends EventListener {
    void invoke(T arg);
}
