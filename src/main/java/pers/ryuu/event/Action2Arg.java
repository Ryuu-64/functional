package pers.ryuu.event;


public final class Action2Arg<T1, T2> extends MulticastDelegate<IAction2Arg<T1, T2>> {
    public void invoke(T1 arg1, T2 arg2) {
        for (index = 0; index < delegates.size(); index++) {
            delegates.get(index).invoke(arg1, arg2);
        }
    }
}