package pers.ryuu.event;

import java.util.EventListener;

public interface IAction extends EventListener {
    void invoke();
}
