package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IFunc<TResult> extends EventListener {
    TResult invoke();
}