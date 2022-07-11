package pers.ryuu.event;

import java.util.EventListener;

public interface IFunc2Arg<T1, T2, TResult> extends EventListener {
    TResult invoke(T1 arg1, T2 arg2);
}