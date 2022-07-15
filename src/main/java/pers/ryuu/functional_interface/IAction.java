package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IAction extends EventListener {
    void invoke();
}
