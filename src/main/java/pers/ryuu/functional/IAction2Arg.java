package pers.ryuu.functional;

public interface IAction2Arg<T1, T2> extends Unicast {
    void invoke(T1 arg1, T2 arg2);
}
