package pers.ryuu.delegate;

import java.util.EventListener;

public interface IAction extends EventListener {
    void invoke();
}
