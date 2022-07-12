package pers.ryuu.delegate;

public class Func<TResult> extends MulticastDelegate<IFunc<TResult>> {

    public TResult invoke() {
        TResult result = null;
        for (index = 0; index < delegates.size(); index++) {
            result = delegates.get(index).invoke();
        }
        return result;
    }
}