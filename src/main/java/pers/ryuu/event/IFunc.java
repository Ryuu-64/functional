package pers.ryuu.event;

import java.util.EventListener;

public interface IFunc<TResult> extends EventListener {
    TResult invoke();
}