package pers.ryuu.functional;

@FunctionalInterface
public interface IEventHandler<TSender, TEventArgs extends EventArgs> extends Unicast {
    void invoke(TSender sender, TEventArgs args);
}