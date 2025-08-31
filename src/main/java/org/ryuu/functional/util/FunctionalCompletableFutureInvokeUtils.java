package org.ryuu.functional.util;

import org.ryuu.functional.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public final class FunctionalCompletableFutureInvokeUtils {
    private FunctionalCompletableFutureInvokeUtils() {
    }

    public static void invokeAsync(Action action) {
        CompletableFuture.runAsync(action::invoke);
    }

    public static void invokeAsync(Action action, Executor executor) {
        CompletableFuture.runAsync(action::invoke, executor);
    }

    public static <T> void invokeAsync(Action1Arg<T> action, T arg) {
        CompletableFuture.runAsync(() -> action.invoke(arg));
    }

    public static <T> void invokeAsync(Action1Arg<T> action, T arg, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg), executor);
    }

    public static <T1, T2> void invokeAsync(Action2Args<T1, T2> action, T1 arg1, T2 arg2) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2));
    }

    public static <T1, T2> void invokeAsync(Action2Args<T1, T2> action, T1 arg1, T2 arg2, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2), executor);
    }

    public static <T1, T2, T3> void invokeAsync(Action3Args<T1, T2, T3> action, T1 arg1, T2 arg2, T3 arg3) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3));
    }

    public static <T1, T2, T3> void invokeAsync(Action3Args<T1, T2, T3> action, T1 arg1, T2 arg2, T3 arg3, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3), executor);
    }

    public static <T1, T2, T3, T4> void invokeAsync(Action4Args<T1, T2, T3, T4> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4));
    }

    public static <T1, T2, T3, T4> void invokeAsync(Action4Args<T1, T2, T3, T4> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4), executor);
    }

    public static <T1, T2, T3, T4, T5> void invokeAsync(Action5Args<T1, T2, T3, T4, T5> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5));
    }

    public static <T1, T2, T3, T4, T5> void invokeAsync(Action5Args<T1, T2, T3, T4, T5> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5), executor);
    }

    public static <T1, T2, T3, T4, T5, T6> void invokeAsync(Action6Args<T1, T2, T3, T4, T5, T6> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5, arg6));
    }

    public static <T1, T2, T3, T4, T5, T6> void invokeAsync(Action6Args<T1, T2, T3, T4, T5, T6> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5, arg6), executor);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> void invokeAsync(Action7Args<T1, T2, T3, T4, T5, T6, T7> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7));
    }

    public static <T1, T2, T3, T4, T5, T6, T7> void invokeAsync(Action7Args<T1, T2, T3, T4, T5, T6, T7> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7), executor);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> void invokeAsync(Action8Args<T1, T2, T3, T4, T5, T6, T7, T8> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> void invokeAsync(Action8Args<T1, T2, T3, T4, T5, T6, T7, T8> action, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8, Executor executor) {
        CompletableFuture.runAsync(() -> action.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8), executor);
    }

    public static <T> CompletableFuture<T> invokeAsync(Func<T> func) {
        return CompletableFuture.supplyAsync(func::invoke);
    }

    public static <T> CompletableFuture<T> invokeAsync(Func<T> func, Executor executor) {
        return CompletableFuture.supplyAsync(func::invoke, executor);
    }

    public static <T, TResult> CompletableFuture<TResult> invokeAsync(Func1Arg<T, TResult> func, T arg) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg));
    }

    public static <T, TResult> CompletableFuture<TResult> invokeAsync(Func1Arg<T, TResult> func, T arg, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg), executor);
    }

    public static <T1, T2, TResult> CompletableFuture<TResult> invokeAsync(Func2Args<T1, T2, TResult> func, T1 arg1, T2 arg2) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2));
    }

    public static <T1, T2, TResult> CompletableFuture<TResult> invokeAsync(Func2Args<T1, T2, TResult> func, T1 arg1, T2 arg2, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2), executor);
    }

    public static <T1, T2, T3, TResult> CompletableFuture<TResult> invokeAsync(Func3Args<T1, T2, T3, TResult> func, T1 arg1, T2 arg2, T3 arg3) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3));
    }

    public static <T1, T2, T3, TResult> CompletableFuture<TResult> invokeAsync(Func3Args<T1, T2, T3, TResult> func, T1 arg1, T2 arg2, T3 arg3, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3), executor);
    }

    public static <T1, T2, T3, T4, TResult> CompletableFuture<TResult> invokeAsync(Func4Args<T1, T2, T3, T4, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4));
    }

    public static <T1, T2, T3, T4, TResult> CompletableFuture<TResult> invokeAsync(Func4Args<T1, T2, T3, T4, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4), executor);
    }

    public static <T1, T2, T3, T4, T5, TResult> CompletableFuture<TResult> invokeAsync(Func5Args<T1, T2, T3, T4, T5, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5));
    }

    public static <T1, T2, T3, T4, T5, TResult> CompletableFuture<TResult> invokeAsync(Func5Args<T1, T2, T3, T4, T5, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5), executor);
    }

    public static <T1, T2, T3, T4, T5, T6, TResult> CompletableFuture<TResult> invokeAsync(Func6Args<T1, T2, T3, T4, T5, T6, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5, arg6));
    }

    public static <T1, T2, T3, T4, T5, T6, TResult> CompletableFuture<TResult> invokeAsync(Func6Args<T1, T2, T3, T4, T5, T6, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5, arg6), executor);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, TResult> CompletableFuture<TResult> invokeAsync(Func7Args<T1, T2, T3, T4, T5, T6, T7, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, TResult> CompletableFuture<TResult> invokeAsync(Func7Args<T1, T2, T3, T4, T5, T6, T7, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7), executor);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, TResult> CompletableFuture<TResult> invokeAsync(Func8Args<T1, T2, T3, T4, T5, T6, T7, T8, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, TResult> CompletableFuture<TResult> invokeAsync(Func8Args<T1, T2, T3, T4, T5, T6, T7, T8, TResult> func, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8, Executor executor) {
        return CompletableFuture.supplyAsync(() -> func.invoke(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8), executor);
    }
}
