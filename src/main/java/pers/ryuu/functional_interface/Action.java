package pers.ryuu.functional_interface;

public final class Action extends MulticastDelegate<IAction> {

    public void invoke() {
        for (index = 0; index < delegates.size(); index++) {
            delegates.get(index).invoke();
        }
    }
}