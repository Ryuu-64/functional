package pers.ryuu.functional;

public interface IEventHandler<TSender, TEventArgs extends EventArgs> extends Unicast {
    void invoke(TSender sender, TEventArgs args);
}