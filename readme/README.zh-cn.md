# functional

[![](https://jitpack.io/v/Ryuu-64/Functional.svg)](https://jitpack.io/#Ryuu-64/Functional)[![](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f95a917e82d34e74a03b51114e9d11ec)](https://www.codacy.com/gh/Ryuu-64/Functional/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Ryuu-64/Functional&amp;utm_campaign=Badge_Grade)

## 这是什么？

1. 预定义泛型函数式接口[^1]：

    1. 无参数到至多 8 个参数**无返回值
       ** [IAction](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IAction.java) ~ [IAction8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IAction8Arg.java)

    2. 无参数到至多 8 个参数**含返回值
       ** [IFunc](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IFunc.java) ~ [IFunc8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IFunc8Arg.java)

    3. [IEventHandler](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/IEventHandler.java)[^2]

2. 多播泛型函数式接口[^3]：

    1. 无参数到至多 8 个参数**无返回值
       ** [Action](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Action.java) ~ [Action8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Action8Arg.java)

    2. 无参数到至多 8 个参数**含返回值
       ** [Func](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Func.java) ~ [Func8Arg](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Func8Arg.java)

    3. [EventHandler](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventHandler.java)

   多播泛型函数式接口是对预定义泛型函数式接口集合的封装，调用多播函数接口的 invoke[^4]，将会依次调用该集合中的所有函数式接口的
   invoke。其支持[添加](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L12)、[删除](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L23)、[contains](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L34)[^5]、[equals](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L134)
   及 [hashCode](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/Multicast.java#L146)
   操作，支持 invoke 期间的添加及修改操作，多线程安全。

## 为何使用？

1. 预定义常用的泛型函数式接口：
    1. 将函数式接口和实现隔离。
    2. 减少类数量。

2. 多播泛型函数式接口：

    1. 方便开发者基于事件编程[^6]。
    2. 减少集合实现代码。

## 详细

1. 过多的函数式接口

   自 java
   8 [Lambda 表达式 (Lambda Expressions)](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
   加入后，开发者们很容易在有需要用到 Lambda 表达式时就声明一个新的函数式接口。声明的函数式接口越来越多，*
   *但除接口名及接口中方法名可能不同外，接口中方法的返回值及参数表都相同**
   。没有必要定义这么多实质相同函数式接口，复用此库中预设的函数式接口，减少系统中的类数量，简化代码。

2. 反复编写函数式接口集合

   编写如[观察者模式 (Observer Pattern)](https://en.wikipedia.org/wiki/Observer_pattern)
   和[发布订阅模式 (Publish-subscribe Pattern)](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern)
   实现时，需要维护一个函数式接口的集合，实现多播。而这样的实践非常常见，若每次需要时就编写一次函数式接口集合的实现，会出现许多非必要的重复的代码段。使用此库中的多播泛型函数式接口，无需反复编写多播实现。

3. 非必要的耦合

   若在使用函数式接口时直接专为特定实现声明一个新的函数式接口，此函数式接口会与特定实现的 artifact
   耦合。然而这种耦合是非必要的，使用此库中的函数式接口，让函数式接口与实现解耦合。

## 关于

## 其他

### 微基准测试结果

```
Benchmark                        (baseActions)   Mode  Cnt   Score   Error   Units
MultithreadBenchmark.mix                    32  thrpt   64  11.635 ± 1.392  ops/us
MultithreadBenchmark.mix:add                32  thrpt   64   0.649 ± 0.091  ops/us
MultithreadBenchmark.mix:invoke             32  thrpt   64   9.719 ± 1.398  ops/us
MultithreadBenchmark.mix:remove             32  thrpt   64   1.267 ± 0.074  ops/us
```

### 联系我

如果您发现任何错误或有任何建议，请联系[我 (ryuu)](64ryuu@gmail.com)。

## 脚注

[^1]:C# 泛型为具象化泛型 (reified generics)，可定义接口名相同但泛型参数不同的类。java
泛型为非具象化泛型，不可定义接口名相同但泛型参数不同的类。详情见 [Type erasure versus reified generics](https://en.wikipedia.org/wiki/Comparison_of_C_Sharp_and_Java#Type_erasure_versus_reified_generics)<br>
.NET 中类似 Java Lambda
表达式的[函数式编程 (Functional programming)](https://en.wikipedia.org/wiki/Functional_programming)
实现被称为[委托 (delegate)](https://docs.microsoft.com/zh-cn/dotnet/csharp/delegate-class)。不同于 Java 函数式接口，.NET
中的委托默认为多播委托，并且 .NET 声明了从无参到 16 参，从无返回值到有返回值的各种泛型委托。因此，开发者可使用 .NET
已声明的委托，不必再自行声明委托。详情见 [CSharp-委托](https://blog.ryuu64.top/CSharp-%E5%A7%94%E6%89%98/)，[Functional programming](https://en.wikipedia.org/wiki/Comparison_of_C_Sharp_and_Java#Functional_programming)。

[^2]:
此函数式接口参数表为 [(TSender sender, TEventArgs arg)](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventHandler.java#L7)
，详情见 [EventHandler.java](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventHandler.java)
及 [**EventArgs.java
**](https://github.com/Ryuu-64/Functional/blob/main/src/main/java/org/ryuu/functional/EventArgs.java)。

[^3]:**java 不允许开发者自定义运算符重载**，因此多播泛型函数式接口的操作没有如 C# 中委托的 +、+=、- 及 -= 等语法糖。

[^4]:执行函数式接口不是**调用 (call)** 目标方法，而是**援引 (invoke)** 目标方法。

[^5]:特别的，若 contains 入参为多播，将会判断当前多播中是有入参多播集合元素的连续子序列

[^6]:如[观察者模式 (Observer Pattern)](https://en.wikipedia.org/wiki/Observer_pattern)
和[发布订阅模式 (Publish-subscribe Pattern)](https://en.wikipedia.org/wiki/Publish-subscribe_pattern)。