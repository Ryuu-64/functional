package pers.ryuu.functional;

public interface IFunc2Arg<T1, T2, TResult> extends Unicast {
    TResult invoke(T1 arg1, T2 arg2);
}