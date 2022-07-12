package pers.ryuu.delegate;

public final class Action extends MulticastDelegate<IAction> {

    public void invoke() {
        for (index = 0; index < delegates.size(); index++) {
            delegates.get(index).invoke();
        }
    }
}