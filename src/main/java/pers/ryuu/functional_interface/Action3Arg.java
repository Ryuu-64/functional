package pers.ryuu.functional_interface;

public final class Action3Arg<T1, T2, T3> extends MulticastFunctionalInterface<IAction3Arg<T1, T2, T3>> {
    public void invoke(T1 arg1, T2 arg2, T3 arg3) {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke(arg1, arg2, arg3);
        }
    }
}