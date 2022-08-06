package pers.ryuu.functional;

@FunctionalInterface
public interface IAction3Arg<T1, T2, T3> extends Unicast {
    void invoke(T1 arg1, T2 arg2, T3 arg3);
}