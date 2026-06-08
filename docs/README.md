# Project Docs

This directory contains maintainer-facing project notes for `functional`.

## Index

- [Architecture overview](./architecture/overview.md)
- [Development and testing conventions](./references/conventions.md)

## Project Snapshot

`functional` is a Java library that brings C#-style multicast delegates and
events to Java. The public API is organized around:

- `Action` and `Actions` variants for no-return callbacks.
- `Func` and `Funcs` variants for callbacks with return values.
- `EventHandler`, `EventHandlers`, and `EventArgs` for event-style APIs.
- `Delegate` and `Event` as the core multicast implementations.
- Utility helpers under `org.ryuu.functional.util`.

The project is built with Gradle and targets Java 8 through the Gradle Java
toolchain.

## Common Commands

From the repository root:

```powershell
.\gradlew.bat test
.\gradlew.bat build
.\gradlew.bat jmh
```

On Unix-like shells:

```sh
./gradlew test
./gradlew build
./gradlew jmh
```

The test task uses JUnit Platform and sets `file.encoding=UTF-8`.
