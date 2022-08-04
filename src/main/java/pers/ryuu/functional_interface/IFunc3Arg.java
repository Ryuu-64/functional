package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IFunc3Arg<T1, T2, T3, TResult> extends IFunctionalInterface {
    TResult invoke(T1 arg1, T2 arg2, T3 arg3);
}