package pers.ryuu.functional_interface;

public class Func1Arg<T1, TResult> extends MulticastFunctionalInterface<IFunc1Arg<T1, TResult>> {
    public TResult invoke(T1 arg1) {
        TResult result = null;
        for (index = 0; index < interfaces.size(); index++) {
            result = interfaces.get(index).invoke(arg1);
        }
        return result;
    }
}