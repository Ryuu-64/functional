package pers.ryuu.event;

public class Func1Arg<T1, TResult> extends MulticastDelegate<IFunc1Arg<T1, TResult>> {
    public TResult invoke(T1 arg1) {
        TResult result = null;
        for (index = 0; index < delegates.size(); index++) {
            result = delegates.get(index).invoke(arg1);
        }
        return result;
    }
}