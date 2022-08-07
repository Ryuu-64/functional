package org.ryuu.functional;

public final class Action5Arg<T1, T2, T3, T4, T5>
        extends Multicast<IAction5Arg<T1, T2, T3, T4, T5>>
        implements IAction5Arg<T1, T2, T3, T4, T5> {
    @Override
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        for (IAction5Arg<T1, T2, T3, T4, T5> functional : this) {
            functional.invoke(arg1, arg2, arg3, arg4, arg5);
        }
    }
}