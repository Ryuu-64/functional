# Functional

[简体中文](https://github.com/Ryuu-64/Functional/blob/main/README.zh-cn.md)

[![](https://jitpack.io/v/Ryuu-64/Functional.svg)](https://jitpack.io/#Ryuu-64/Functional)[![](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f95a917e82d34e74a03b51114e9d11ec)](https://www.codacy.com/gh/Ryuu-64/Functional/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Ryuu-64/Functional&amp;utm_campaign=Badge_Grade)

## what is this?

1. Predefined generic functional interface[^1]:

   1. No parameters up to 8 parameters **no return value** [IAction](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IAction.java) ~ [IAction8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IAction8Arg.java)

   2. No parameters to up to 8 parameters **with return value** [IFunc](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IFunc.java) ~ [IFunc8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IFunc8Arg.java)

   3. [IEventHandler](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IEventHandler.java)[^2]

2. Multicast generic functional interface[^3]:

   1. No parameters to up to 8 parameters **no return value** [Action](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Action.java) ~ [Action8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Action8Arg.java)

   2. No parameters to up to 8 parameters **with return value** [Func](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Func.java) ~ [Func8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Func8Arg.java)

   3. [EventHandler](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventHandler.java)

   The multicast generic functional interface is the encapsulation of a set of predefined generic functional interfaces. Calling the invoke[^4] of the multicast functional interface will call the invoke of all functional interfaces in the set in turn. It supports [added](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L12), [removed](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L23), [contains](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L34)[^5], [equals](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L134) and [hashCode](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L146) operation, supports adding and modifying operations during invoke, multi-thread safety.


## Why use?

1. Predefine commonly used generic functional interfaces:
   1. Isolate the functional interface from the implementation.
   2. Reduce the number of classes.

2. Multicast generic functional interface:

   1. It is convenient for developers to program based on events[^6].
   2. Reduce collection implementation code.

## details

1. Too many functional interfaces

   Since the addition of java 8 [Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html), it is easy for developers to use lambda expressions when needed declares a new functional interface. More and more functional interfaces are declared, **but except that the interface name and the method name in the interface may be different, the return value and parameter list of the method in the interface are the same**. There is no need to define so many substantially identical functional interfaces, reuse the preset functional interfaces in this library, reduce the number of classes in the system, and simplify the code.

2. Iteratively write functional interface collections

   Write like [Observer Pattern](https://en.wikipedia.org/wiki/Observer_pattern) and [Publish-subscribe Pattern](https://en.wikipedia.org/wiki/Publish-subscribe_pattern), you need to maintain a collection of functional interfaces to implement multicast. And this practice is very common, and if you write an implementation of a functional interface collection every time you need it, there will be many unnecessary duplicate code segments. Using the multicast generic functional interface in this library eliminates the need to repeatedly write multicast implementations.

3. Unnecessary coupling

   If you declare a new functional interface specifically for a specific implementation when using a functional interface, the functional interface is coupled to the implementation-specific artifact. However this coupling is not necessary, use the functional interface in this library to decouple the functional interface from the implementation.

## about

### contact me

If you find any bugs or have any suggestions, please contact [me (ryuu)](64ryuu@gmail.com).

## Footnotes

[^1]: C# generics are reified generics, which can define classes with the same interface name but different generic parameters. Java generics are non-reified generics, classes with the same interface name but different generic parameters cannot be defined. For details, see [Type erasure versus reified generics](https://en.wikipedia.org/wiki/Comparison_of_C_Sharp_and_Java#Type_erasure_versus_reified_generics)<br>[Functional programming](https://en.wikipedia.org/wiki/Functional_programming) like Java Lambda expressions in .NET implementation is called [delegate](https://docs.microsoft.com/en-us/dotnet/csharp/delegate-class). Unlike Java functional interfaces, delegates in .NET default to multicast delegates, and .NET declares a variety of generic delegates ranging from no parameters to 16 parameters, from no return value to return value. Therefore, developers can use .NET's declared delegates instead of declaring their own. For details, see [CSharp-Delegate](https://blog.ryuu64.top/CSharp-%E5%A7%94%E6%89%98/), [Functional programming](https://en.wikipedia.org/wiki/Comparison_of_C_Sharp_and_Java#Functional_programming).

[^2]: The parameter table of this functional interface is [(TSender sender, TEventArgs arg)](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventHandler.java#L7), see [EventHandler.java](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventHandler.java) and [**EventArgs.java**](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventArgs.java) for details.

[^3]: **java does not allow developers to define operator overloading**, so the operation of multicast generic functional interface does not have syntactic sugar such as +, +=, - and -= delegated in C#.

[^4]: Implementing a functional interface is not **calling** the target method, but **invokeing** the target method.

[^5]: In particular, if the input of contains is a multicast, it will be judged that the current multicast is a continuous subsequence of the elements of the multicast set with the input of the input.

[^6]: Such as [Observer Pattern](https://en.wikipedia.org/wiki/Observer_pattern) and [Publish-subscribe Pattern](https://en.wikipedia.org/wiki/Publish-subscribe_pattern).