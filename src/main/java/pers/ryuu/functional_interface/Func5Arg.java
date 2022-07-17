package pers.ryuu.functional_interface;

public class Func5Arg<T1, T2, T3, T4, T5, TResult> extends MulticastFunctionalInterface<IFunc5Arg<T1, T2, T3, T4, T5, TResult>> {
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        TResult result = null;
        for (index = 0; index < interfaces.size(); index++) {
            result = interfaces.get(index).invoke(arg1, arg2, arg3, arg4, arg5);
        }
        return result;
    }
}
