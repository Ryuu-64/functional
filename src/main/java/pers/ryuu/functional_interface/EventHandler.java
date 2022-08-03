package pers.ryuu.functional_interface;

public class EventHandler<TSender, TEventArgs extends EventArgs> extends MulticastFunctionalInterface<IEventHandler<TSender, TEventArgs>> implements IEventHandler<TSender, TEventArgs> {
    @Override
    public void invoke(TSender sender, TEventArgs args) {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke(sender, args);
        }
    }
}
