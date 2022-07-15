package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IAction2Arg<T1, T2> extends EventListener {
    void invoke(T1 arg1, T2 arg2);
}
