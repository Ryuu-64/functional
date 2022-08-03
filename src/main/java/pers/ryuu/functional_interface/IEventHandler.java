package pers.ryuu.functional_interface;

import java.util.EventListener;

public interface IEventHandler<TSender, TEventArgs extends EventArgs> extends EventListener {
    void invoke(TSender sender, TEventArgs args);
}