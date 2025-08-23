package org.ryuu.functional;

public final class EventHandlers<TSender, TEventArgs extends EventArgs>
        extends MulticastDelegate<EventHandler<TSender, TEventArgs>>
        implements EventHandler<TSender, TEventArgs> {
    @Override
    public void invoke(TSender sender, TEventArgs args) {
        for (EventHandler<TSender, TEventArgs> eventHandler : this) {
            eventHandler.invoke(sender, args);
        }
    }
}