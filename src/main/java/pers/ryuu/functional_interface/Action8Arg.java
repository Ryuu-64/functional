package pers.ryuu.functional_interface;

public final class Action8Arg<T1, T2, T3, T4, T5, T6, T7, T8> extends MulticastFunctionalInterface<IAction8Arg<T1, T2, T3, T4, T5, T6, T7, T8>> {
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8) {
        for (index = 0; index < interfaces.size(); index++) {
            interfaces.get(index).invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
    }
}