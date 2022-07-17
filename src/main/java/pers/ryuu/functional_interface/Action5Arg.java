package pers.ryuu.functional_interface;

public final class Action5Arg<T1, T2, T3, T4, T5> extends MulticastFunctionalInterface<IAction5Arg<T1, T2, T3, T4, T5>> {
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke(arg1, arg2, arg3, arg4, arg5);
        }
    }
}