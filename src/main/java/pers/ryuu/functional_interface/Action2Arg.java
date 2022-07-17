package pers.ryuu.functional_interface;


public final class Action2Arg<T1, T2> extends MulticastFunctionalInterface<IAction2Arg<T1, T2>> {
    public void invoke(T1 arg1, T2 arg2) {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke(arg1, arg2);
        }
    }
}