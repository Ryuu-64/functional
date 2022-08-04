package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IFunc2Arg<T1, T2, TResult> extends IFunctionalInterface {
    TResult invoke(T1 arg1, T2 arg2);
}