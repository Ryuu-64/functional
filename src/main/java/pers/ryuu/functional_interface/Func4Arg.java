package pers.ryuu.functional_interface;

public class Func4Arg<T1, T2, T3, T4, TResult> extends MulticastFunctionalInterface<IFunc4Arg<T1, T2, T3, T4, TResult>> {
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        TResult result = null;
        for (index = 0; index < interfaces.size(); index++) {
            result = interfaces.get(index).invoke(arg1, arg2, arg3, arg4);
        }
        return result;
    }
}