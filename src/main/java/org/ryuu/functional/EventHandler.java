package org.ryuu.functional;

@FunctionalInterface
public interface EventHandler<TSender, TEventArgs extends EventArgs> extends Unicast {
    void invoke(TSender sender, TEventArgs args);
}