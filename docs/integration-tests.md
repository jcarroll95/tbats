# Integration Tests

This project has two integration tests in `src/test/java/com/jcarroll95/tbats/`:

- `GrantLifecycleIntegrationTest` — login, create grant, list active, revoke,
  verify removed.
- `GrantExpirationIntegrationTest` — verifies the scheduled `GrantExpirationJob`
  flips expired grants to revoked.

Both use `@SpringBootTest(webEnvironment = RANDOM_PORT)` and exercise the full
HTTP → security filter → controller → service → JPA → Postgres path.

## How they work

Tests extend `IntegrationTestBase`, which:

1. Starts a **Testcontainers** PostgreSQL 16 container (shared across all test classes via a static initializer)
2. Uses Spring Boot's `@ServiceConnection` to auto-configure the datasource
3. Runs Flyway migrations against the container on startup
4. Provides a `restClient()` helper for HTTP tests

No Docker socket mounts are needed — Ryuk (the cleanup container) is disabled
via the Maven Surefire plugin config because Colima's socket path can't be
mounted inside containers. Test containers are cleaned up by the JVM shutdown
hook instead.

## Running

```bash
./mvnw test
```

Requires Docker (via Docker Desktop or Colima) running locally.

## Key dependencies

- `org.testcontainers:testcontainers-postgresql:2.0.4`
- `org.testcontainers:testcontainers-junit-jupiter:2.0.4`
- `org.springframework.boot:spring-boot-testcontainers`
