package pers.ryuu.functional_interface;

public final class Action4Arg<T1, T2, T3, T4> extends MulticastFunctionalInterface<IAction4Arg<T1, T2, T3, T4>> {
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke(arg1, arg2, arg3, arg4);
        }
    }
}