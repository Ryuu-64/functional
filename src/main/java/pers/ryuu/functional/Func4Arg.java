package pers.ryuu.functional;

public class Func4Arg<T1, T2, T3, T4, TResult>
        extends Multicast<IFunc4Arg<T1, T2, T3, T4, TResult>>
        implements IFunc4Arg<T1, T2, T3, T4, TResult> {
    @Override
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        TResult result = null;
        for (IFunc4Arg<T1, T2, T3, T4, TResult> functional : this) {
            result = functional.invoke(arg1, arg2, arg3, arg4);
        }
        return result;
    }
}