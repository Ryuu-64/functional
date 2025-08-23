package org.ryuu.functional;

@FunctionalInterface
public interface EventHandler<TSender, TEventArgs extends EventArgs> extends Delegate {
    void invoke(TSender sender, TEventArgs args);
}