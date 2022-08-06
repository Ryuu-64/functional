package pers.ryuu.functional;

public final class Action4Arg<T1, T2, T3, T4>
        extends Multicast<IAction4Arg<T1, T2, T3, T4>>
        implements IAction4Arg<T1, T2, T3, T4> {
    @Override
    public void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        for (IAction4Arg<T1, T2, T3, T4> functional : this) {
            functional.invoke(arg1, arg2, arg3, arg4);
        }
    }
}