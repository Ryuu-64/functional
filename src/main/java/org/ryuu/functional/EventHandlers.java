package org.ryuu.functional;

public final class EventHandlers<TSender, TEventArgs extends EventArgs>
        extends MulticastDelegate<EventHandler<TSender, TEventArgs>>
        implements EventHandler<TSender, TEventArgs> {
    private EventHandlers(boolean isEvent) {
        super(isEvent);
    }

    public static <TSender, TEventArgs extends EventArgs> EventHandlers<TSender, TEventArgs> delegate() {
        return new EventHandlers<>(false);
    }

    public static <TSender, TEventArgs extends EventArgs> EventHandlers<TSender, TEventArgs> event() {
        return new EventHandlers<>(true);
    }

    @Override
    public void invoke(TSender sender, TEventArgs args) {
        for (EventHandler<TSender, TEventArgs> eventHandler : this) {
            eventHandler.invoke(sender, args);
        }
    }
}