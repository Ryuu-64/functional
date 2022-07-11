package pers.ryuu.event;

public class Func3Arg<T1, T2, T3, TResult> extends MulticastDelegate<IFunc3Arg<T1, T2, T3, TResult>> {
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3) {
        TResult result = null;
        for (index = 0; index < delegates.size(); index++) {
            result = delegates.get(index).invoke(arg1, arg2, arg3);
        }
        return result;
    }
}