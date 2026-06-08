# Architecture Overview

`functional` is intentionally small: most types are public interfaces for
different callback arities, backed by a compact multicast implementation.

## Core Model

- `Delegate<T>` defines the shared multicast operations: add, remove,
  contains, clear, count, get a delegate snapshot, and invoke.
- `MulticastDelegate<T>` stores the callback list and provides the common
  add/remove/contains/equality behavior.
- `Event<T>` extends the multicast behavior with synchronization for
  thread-safe event-style use.

The API exposes two creation modes through the arity-specific facade types:

- `delegate()` creates a high-performance, non-thread-safe multicast delegate.
- `event()` creates a synchronized event variant for multi-threaded publisher
  and subscriber use cases.

## Arity Families

The public callback API is split into no-return and return-value families.

- `Action`, `Action1Arg`, ... `Action8Args`
- `Func`, `Func1Arg`, ... `Func8Args`
- `Actions`, `Actions1Arg`, ... `Actions8Args`
- `Funcs`, `Funcs1Arg`, ... `Funcs8Args`

This keeps each public interface explicit and Java 8 friendly instead of using
reflection, arrays, or varargs for the primary callback path.

## Return Values

Multicast `Func` variants invoke every callback in order and return the last
callback result. Earlier callback results are ignored. This behavior is part of
the public contract and is covered by tests.

## Thread Safety Boundary

The library keeps the thread-safety choice explicit:

- Use `delegate()` when all mutations and invocations are externally confined
  to one thread or otherwise synchronized by the caller.
- Use `event()` when callbacks may be added, removed, or invoked from multiple
  threads.

Do not make the delegate variants implicitly synchronized; that would blur the
performance and behavior distinction exposed by the factory methods.

## Utility Package

`org.ryuu.functional.util` contains invocation helpers, including null-safe
invocation helpers and `CompletableFuture` async invocation helpers. These
utilities should remain thin adapters over the public functional interfaces.
