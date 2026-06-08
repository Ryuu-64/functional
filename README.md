# functional

[English](./README.md) | [简体中文](./readme/README.zh-cn.md)

[![](https://jitpack.io/v/Ryuu-64/Functional.svg)](https://jitpack.io/#Ryuu-64/Functional)
[![](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f95a917e82d34e74a03b51114e9d11ec)](https://www.codacy.com/gh/Ryuu-64/Functional/dashboard)

> Multicast Delegate for Java - Bringing C# Delegate experience to Java

Maintainer documentation: [docs/README.md](./docs/README.md)

## Features

- 🚀 **Multicast Delegate** - Invoke multiple methods with one delegate
- 🔒 **Thread Safety** - Built-in synchronization for Event mode
- 📚 **Unified Interfaces** - 0-8 parameter functional interfaces
- 🧩 **Event-Driven** - Complete observer pattern support

## Quick Start

### Maven

```xml
<dependency>
    <groupId>com.github.Ryuu-64</groupId>
    <artifactId>Functional</artifactId>
    <version>6.0.0</version>
</dependency>
```

### Basic Usage

```java
import org.ryuu.functional.*;

// Create multicast delegate
Actions actions = Actions.delegate();

// Add methods
actions.add(() -> System.out.println("Method 1"));
actions.add(() -> System.out.println("Method 2"));

// Invoke all methods
actions.invoke();
// Output:
// Method 1
// Method 2
```

### Event Mode (Thread-Safe)

```java
// Event mode: thread-safe, for pub/sub
Actions event = Actions.event();

// Delegate mode: high performance, not thread-safe
Actions delegate = Actions.delegate();
```

### With Parameters

```java
Actions2Args<String, Integer> actions = Actions2Args.delegate();

actions.add((name, age) -> System.out.println(name + " is " + age + " years old"));
actions.add((name, age) -> System.out.println("Hello, " + name));

actions.invoke("Alice", 25);
// Output:
// Alice is 25 years old
// Hello, Alice
```

### With Return Value

```java
Func2Args<Integer, Integer, Integer> add = Func2Args.delegate();

add.add((a, b) -> a + b);
add.add((a, b) -> a * b); // Multicast returns only the first result

Integer result = add.invoke(3, 4); // Returns 7
```

### Event Handler

```java
// Similar to C# EventHandler
EventHandlers<String, MyEventArgs> handlers = EventHandlers.delegate();

handlers.add((sender, args) -> System.out.println("Handler 1: " + args.message));
handlers.add((sender, args) -> System.out.println("Handler 2: " + args.message));

handlers.invoke("Sender", new MyEventArgs("Hello"));

// Output:
// Handler 1: Hello
// Handler 2: Hello
```

## API Overview

### Predefined Functional Interfaces

| Params | No Return             | With Return            |
|--------|-----------------------|------------------------|
| 0      | `Action`              | `Func<R>`              |
| 1      | `Action1Arg<T>`       | `Func1Arg<T, R>`       |
| 2      | `Action2Args<T1, T2>` | `Func2Args<T1, T2, R>` |
| ...    | ...                   | ...                    |
| 8      | `Action8Args<...>`    | `Func8Args<..., R>`    |

### Multicast Interfaces

| Type                 | Factory | Thread-Safe | Use Case                        |
|----------------------|---------|-------------|---------------------------------|
| `Actions.delegate()` | Static  | ❌ No        | Single-thread, high performance |
| `Actions.event()`    | Static  | ✅ Yes       | Multi-thread, pub/sub           |

### Supported Operations

- `add(T delegate)` - Add delegate
- `remove(T delegate)` - Remove delegate
- `contains(T delegate)` - Check if contains
- `clear()` - Clear all delegates
- `count()` - Get delegate count
- `getDelegates()` - Get delegates copy
- `invoke(...)` - Invoke all delegates

## Thread Safety

### Event vs Delegate

| Feature     | delegate()                      | event()                  |
|-------------|---------------------------------|--------------------------|
| Thread Safe | ❌ No                            | ✅ Yes                    |
| Performance | High                            | Synchronization overhead |
| Use Case    | Single-thread, high performance | Multi-thread, pub/sub    |

### Usage Guidelines

```java
// ✅ Correct: Use Delegate in single thread
Actions delegates = Actions.delegate();
delegates.add(() -> System.out.println("1"));
delegates.invoke();

// ✅ Correct: Use Event in multi-thread
Actions events = Actions.event();
events.add(() -> System.out.println("1"));
// Multiple threads can safely call
events.invoke();

// ❌ Wrong: Delegate in multi-thread loses operations
Actions delegates = Actions.delegate();
// ThreadA: delegates.add(action1)
// ThreadB: delegates.add(action2)
// Only one may be added
```

## Performance Benchmarks

```
Benchmark                    Mode  Cnt   Score   Units
─────────────────────────────────────────────────────────
MultithreadBenchmark.mix    thrpt   64   11.635  ops/us
MultithreadBenchmark.mix:add      64    0.649  ops/us
MultithreadBenchmark.mix:invoke   64    9.719  ops/us
MultithreadBenchmark.mix:remove  64    1.267  ops/us
```

## FAQ

### Q: Why `invoke` instead of `call`?

Following C# terminology. Executing a functional interface is "invoking" the target method, not "calling" it.

### Q: What's the difference between Delegate and Event?

- `Delegate` uses Copy-on-Write, higher performance, but **not thread-safe**
- `Event` uses `synchronized`, **thread-safe**, suitable for multi-threaded scenarios

### Q: How are return values handled in multicast?

Multicast delegate only returns the value from the first executed delegate. Other return values are ignored.

### Q: Can I modify the delegate collection during invoke?

It's supported, but behavior may be undefined. It's recommended to complete all add/remove operations before invoking.

## Acknowledgments

Inspired by .NET's `Delegate` and C# event model.

---

Questions or suggestions? Contact [ryuu](64ryuu@gmail.com)
