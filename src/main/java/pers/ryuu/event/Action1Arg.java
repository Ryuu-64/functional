package pers.ryuu.event;

public final class Action1Arg<T> extends MulticastDelegate<IAction1Arg<T>> {

    public void invoke(T arg1) {
        for (index = 0; index < delegates.size(); index++) {
            delegates.get(index).invoke(arg1);
        }
    }
}