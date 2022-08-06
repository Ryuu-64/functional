package pers.ryuu.functional;

public class Func7Arg<T1, T2, T3, T4, T5, T6, T7, TResult>
        extends Multicast<IFunc7Arg<T1, T2, T3, T4, T5, T6, T7, TResult>>
        implements IFunc7Arg<T1, T2, T3, T4, T5, T6, T7, TResult> {
    @Override
    public TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7) {
        TResult result = null;
        for (IFunc7Arg<T1, T2, T3, T4, T5, T6, T7, TResult> functional : this) {
            result = functional.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
        return result;
    }
}