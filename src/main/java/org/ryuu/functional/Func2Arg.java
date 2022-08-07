package org.ryuu.functional;


public class Func2Arg<T1, T2, TResult>
        extends Multicast<IFunc2Arg<T1, T2, TResult>>
        implements IFunc2Arg<T1, T2, TResult> {
    @Override
    public TResult invoke(T1 arg1, T2 arg2) {
        TResult result = null;
        for (IFunc2Arg<T1, T2, TResult> functional : this) {
            result = functional.invoke(arg1, arg2);
        }
        return result;
    }
}