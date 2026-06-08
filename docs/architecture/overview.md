# Architecture Overview

`functional` is intentionally small: most types are public interfaces for
different callback arities, backed by a compact multicast implementation.

## Core Model

- `Delegate` is the package-local marker interface shared by all callback
  interfaces and multicast implementations.
- `MulticastDelegate<T>` stores the callback list and provides the common
  add/remove/contains/equality behavior.
- `Event<T>` is the public subscription interface exposed by event-style APIs.
  It intentionally only allows callers to add and remove delegates.

The API exposes two creation modes through the arity-specific facade types:

- `delegate()` creates a high-performance, non-thread-safe multicast delegate.
- `event()` creates a multicast implementation that synchronizes subscription
  changes for multi-threaded publisher and subscriber use cases.

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

All multicast invocation variants use snapshot semantics. Subscription changes
made during invocation do not affect the current invocation; they take effect
on subsequent invocations.

## Thread Safety Boundary

The library keeps the thread-safety choice explicit:

- Use `delegate()` when all subscription changes are externally confined to one
  thread or otherwise synchronized by the caller.
- Use `event()` when callbacks may be added or removed from multiple threads.
  Both modes use a copy-on-write snapshot list; `event()` adds synchronization
  around `add` and `remove`.

Do not make the delegate variants implicitly synchronized; that would blur the
performance and behavior distinction exposed by the factory methods.

## Utility Package

`org.ryuu.functional.util` contains invocation helpers, including null-safe
invocation helpers and `CompletableFuture` async invocation helpers. These
utilities should remain thin adapters over the public functional interfaces.
