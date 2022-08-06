package pers.ryuu.functional;

public final class Action2Arg<T1, T2>
        extends Multicast<IAction2Arg<T1, T2>>
        implements IAction2Arg<T1, T2> {
    @Override
    public void invoke(T1 arg1, T2 arg2) {
        for (IAction2Arg<T1, T2> functional : this) {
            functional.invoke(arg1, arg2);
        }
    }
}