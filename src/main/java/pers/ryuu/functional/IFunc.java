package pers.ryuu.functional;

public interface IFunc<TResult> extends Unicast {
    TResult invoke();
}