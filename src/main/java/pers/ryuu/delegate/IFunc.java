package pers.ryuu.delegate;

import java.util.EventListener;

public interface IFunc<TResult> extends EventListener {
    TResult invoke();
}