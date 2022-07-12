package pers.ryuu.delegate;

public final class Action3Arg<T1, T2, T3> extends MulticastDelegate<IAction3Arg<T1, T2, T3>> {
    public void invoke(T1 arg1, T2 arg2, T3 arg3) {
        for (index = 0; index < delegates.size(); index++) {
            delegates.get(index).invoke(arg1, arg2, arg3);
        }
    }
}