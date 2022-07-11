package pers.ryuu.event;

import java.util.EventListener;

public interface IAction1Arg<T> extends EventListener {
    void invoke(T arg);
}
