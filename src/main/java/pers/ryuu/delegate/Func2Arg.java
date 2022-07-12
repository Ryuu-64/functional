package pers.ryuu.delegate;


public class Func2Arg<T1, T2, TResult> extends MulticastDelegate<IFunc2Arg<T1, T2, TResult>> {
    public TResult invoke(T1 arg1, T2 arg2) {
        TResult result = null;
        for (index = 0; index < delegates.size(); index++) {
            result = delegates.get(index).invoke(arg1, arg2);
        }
        return result;
    }
}