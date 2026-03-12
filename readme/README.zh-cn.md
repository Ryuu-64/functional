# functional

[English](./README.md) | [简体中文](./readme/README.zh-cn.md)

[![](https://jitpack.io/v/Ryuu-64/Functional.svg)](https://jitpack.io/#Ryuu-64/Functional)
[![](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f95a917e82d34e74a03b51114e9d11ec)](https://www.codacy.com/gh/Ryuu-64/Functional/dashboard)

> Java 版的多播委托 (Multicast Delegate)，填补 Java 缺失的 C# Delegate 体验

## 特性

- 🚀 **多播委托** - 支持一个委托调用多个方法
- 🔒 **线程安全** - Event 模式内置同步机制
- 📚 **统一接口** - 0-8 参数的函数式接口全覆盖
- 🧩 **事件驱动** - 完善的观察者模式支持

## 快速开始

### Maven

```xml
<dependency>
    <groupId>com.github.Ryuu-64</groupId>
    <artifactId>Functional</artifactId>
    <version>6.0.0</version>
</dependency>
```

### 基础用法

```java
import org.ryuu.functional.*;

// 创建多播委托
Actions actions = Actions.delegate();

// 添加方法
actions.add(() -> System.out.println("方法1"));
actions.add(() -> System.out.println("方法2"));

// 调用所有方法
actions.invoke();
// 输出:
// 方法1
// 方法2
```

### 事件模式 (线程安全)

```java
// Event 模式：线程安全，适用于发布订阅
Actions event = Actions.event();

// Delegate 模式：高性能，但非线程安全
Actions delegate = Actions.delegate();
```

### 带参数的方法

```java
Actions2Args<String, Integer> actions = Actions2Args.delegate();

actions.add((name, age) -> System.out.println(name + " is " + age + " years old"));
actions.add((name, age) -> System.out.println("Hello, " + name));

actions.invoke("Alice", 25);
// 输出:
// Alice is 25 years old
// Hello, Alice
```

### 带返回值

```java
Func2Args<Integer, Integer, Integer> add = Func2Args.delegate();

add.add((a, b) -> a + b);
add.add((a, b) -> a * b); // 多播只执行第一个，返回第一个结果

Integer result = add.invoke(3, 4); // 返回 7
```

### 事件处理器

```java
// 类似 C# 的 EventHandler
EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

handlers.add((sender, args) -> System.out.println("Handler 1: " + args.message));
handlers.add((sender, args) -> System.out.println("Handler 2: " + args.message));

handlers.invoke("Sender", new MyEventArgs("Hello"));
// 输出:
// Handler 1: Hello
// Handler 2: Hello
```

## API 概览

### 预定义函数式接口

| 参数数 | 无返回值 | 有返回值 |
|--------|----------|----------|
| 0 | `Action` | `Func<R>` |
| 1 | `Action1Arg<T>` | `Func1Arg<T, R>` |
| 2 | `Action2Args<T1, T2>` | `Func2Args<T1, T2, R>` |
| ... | ... | ... |
| 8 | `Action8Args<...>` | `Func8Args<..., R>` |

### 多播接口

| 类型 | 创建方式 | 线程安全 | 适用场景 |
|------|----------|----------|----------|
| `Actions.delegate()` | 静态工厂 | ❌ 否 | 单线程，高性能 |
| `Actions.event()` | 静态工厂 | ✅ 是 | 多线程，发布订阅 |

### 支持的操作

- `add(T delegate)` - 添加委托
- `remove(T delegate)` - 移除委托
- `contains(T delegate)` - 检查是否包含
- `clear()` - 清空所有委托
- `count()` - 获取委托数量
- `getDelegates()` - 获取委托副本
- `invoke(...)` - 执行所有委托

## 线程安全说明

### Event vs Delegate

| 特性 | delegate() | event() |
|------|------------|---------|
| 线程安全 | ❌ 否 | ✅ 是 |
| 性能 | 高 | 有同步开销 |
| 适用场景 | 单线程，高性能需求 | 多线程，发布订阅 |

### 安全使用准则

```java
// ✅ 正确：单线程使用 Delegate
Actions delegates = Actions.delegate();
delegates.add(() -> System.out.println("1"));
delegates.invoke();

// ✅ 正确：多线程使用 Event
Actions events = Actions.event();
events.add(() -> System.out.println("1"));
// 多个线程可以安全调用
events.invoke();

// ❌ 错误：多线程使用 Delegate 会丢失操作
Actions delegates = Actions.delegate();
// 线程A: delegates.add(action1)
// 线程B: delegates.add(action2)
// 可能只有一个被添加
```

## 性能基准

```
Benchmark                    Mode  Cnt   Score   Units
─────────────────────────────────────────────────────────
MultithreadBenchmark.mix    thrpt   64   11.635  ops/us
MultithreadBenchmark.mix:add      64    0.649  ops/us
MultithreadBenchmark.mix:invoke   64    9.719  ops/us
MultithreadBenchmark.mix:remove   64    1.267  ops/us
```

## 常见问题

### Q: 为什么叫 `invoke` 而不是 `call`?

这是参照 C# 的术语。执行函数式接口是"援引 (invoke)"目标方法，而非"调用 (call)"。

### Q: Delegate 和 Event 有什么区别?

- `Delegate` 采用 Copy-on-Write 策略，性能更高，但**不是线程安全的**
- `Event` 使用 `synchronized` 同步，**线程安全**，适合多线程环境

### Q: 多播委托的返回值如何处理?

多播委托只返回第一个执行的委托的返回值，其他返回值被忽略。

### Q: 可以在 `invoke` 过程中修改委托集合吗?

支持，但行为可能未定义。建议在调用前完成所有添加/移除操作。

## 致谢

灵感来自 .NET 的 `Delegate` 和 C# 的事件模型。

---

有问题或建议？请联系 [ryuu](64ryuu@gmail.com)
