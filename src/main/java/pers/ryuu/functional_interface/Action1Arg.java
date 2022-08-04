package pers.ryuu.functional_interface;

public final class Action1Arg<T> extends MulticastFunctionalInterface<IAction1Arg<T>> implements IAction1Arg<T> {

    public void invoke(T arg1) {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke(arg1);
        }
    }
}