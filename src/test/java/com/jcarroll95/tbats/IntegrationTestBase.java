package com.jcarroll95.tbats;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClient;
import org.testcontainers.postgresql.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "jwt.secret=test-secret-at-least-32-bytes-long-for-integration-tests",
    "spring.jpa.hibernate.ddl-auto=none"
})
public abstract class IntegrationTestBase {

    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    static {
        postgres.start();
    }

    @LocalServerPort
    protected int port;

    protected RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();
    }
}
