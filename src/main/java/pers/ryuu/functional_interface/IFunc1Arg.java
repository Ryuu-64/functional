package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IFunc1Arg<T1, TResult> extends EventListener {
    TResult invoke(T1 arg1);
}