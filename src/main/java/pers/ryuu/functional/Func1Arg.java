package pers.ryuu.functional;

public class Func1Arg<T1, TResult>
        extends Multicast<IFunc1Arg<T1, TResult>>
        implements IFunc1Arg<T1, TResult> {
    @Override
    public TResult invoke(T1 arg1) {
        TResult result = null;
        for (IFunc1Arg<T1, TResult> functional : this) {
            result = functional.invoke(arg1);
        }
        return result;
    }
}