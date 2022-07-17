package pers.ryuu.functional_interface;

public class Func<TResult> extends MulticastFunctionalInterface<IFunc<TResult>> {

    public TResult invoke() {
        TResult result = null;
        for (index = 0; index < interfaces.size(); index++) {
            result = interfaces.get(index).invoke();
        }
        return result;
    }
}