package pers.ryuu.event;

import java.util.EventListener;

public interface IFunc3Arg<T1, T2, T3, TResult> extends EventListener {
    TResult invoke(T1 arg1, T2 arg2, T3 arg3);
}