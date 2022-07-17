package pers.ryuu.functional_interface;

public class Func6Arg<T1, T2, T3, T4, T5, T6, TResult> extends MulticastFunctionalInterface<IFunc6Arg<T1, T2, T3, T4, T5, T6, TResult>> {
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        TResult result = null;
        for (index = 0; index < interfaces.size(); index++) {
            result = interfaces.get(index).invoke(arg1, arg2, arg3, arg4, arg5, arg6);
        }
        return result;
    }
}
