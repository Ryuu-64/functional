package pers.ryuu.functional;

@FunctionalInterface
public interface IFunc1Arg<T1, TResult> extends Unicast {
    TResult invoke(T1 arg1);
}