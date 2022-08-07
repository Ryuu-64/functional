package org.ryuu.functional;

public class EventHandler<TSender, TEventArgs extends EventArgs>
        extends Multicast<IEventHandler<TSender, TEventArgs>>
        implements IEventHandler<TSender, TEventArgs> {
    @Override
    public void invoke(TSender sender, TEventArgs args) {
        for (IEventHandler<TSender, TEventArgs> functional : this) {
            functional.invoke(sender, args);
        }
    }
}