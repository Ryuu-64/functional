package pers.ryuu.functional_interface;

public final class Action extends MulticastFunctionalInterface<IAction> {

    public void invoke() {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke();
        }
    }
}