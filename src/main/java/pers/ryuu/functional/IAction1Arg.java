package pers.ryuu.functional;

public interface IAction1Arg<T> extends Unicast {
    void invoke(T arg);
}
