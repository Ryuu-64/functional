# Development and Testing Conventions

## Runtime and Build

- The project targets Java 8 through Gradle toolchains.
- Use the checked-in Gradle wrapper instead of a system Gradle install.
- Keep source and test compilation encoding as UTF-8.
- JMH benchmarks live under `src/jmh/java` and are configured through the
  `me.champeau.jmh` Gradle plugin.

## Testing

Tests use JUnit 5 and AssertJ. Place tests under `src/test/java` beside the
public package they exercise.

Run the full test suite from the repository root:

```powershell
.\gradlew.bat test
```

The current suite is organized around:

- `ActionsVariantsTest` for no-return arity variants.
- `FuncsVariantsTest` for return-value arity variants.
- `MulticastDelegateTest` for shared delegate behavior.
- `EventTest` and `EventHandlersTest` for event behavior.
- `FunctionalInvokeIfNotNullUtilsTest` for null-safe invocation helpers.
- `FunctionalCompletableFutureInvokeUtilsTest` for async invocation helpers.

When adding behavior, prefer tests that exercise the public API (`Actions`,
`Funcs`, `EventHandlers`, utility methods) rather than private implementation
details.

## Threading Tests

Use `event()` variants for multi-threaded behavior tests. Keep `delegate()`
tests focused on single-threaded behavior unless a test is explicitly proving
the documented non-thread-safe boundary.

Avoid time-sensitive assertions when a deterministic assertion is possible.
If a timeout is needed for async behavior, keep it local to the future or
operation under test.

## API Shape

- Keep arity-specific interfaces explicit from 0 to 8 arguments.
- Maintain both `Action*` and `Func*` families when adding cross-cutting API
  behavior.
- Preserve the `delegate()` vs `event()` factory distinction.
- Keep utility methods as adapters over the public functional interfaces.

## Local Environment Note

The Gradle wrapper requires a usable Java installation. If `.\gradlew.bat test`
fails with `JAVA_HOME is not set and no 'java' command could be found in your
PATH`, install a JDK or set `JAVA_HOME` before using the project test commands.
