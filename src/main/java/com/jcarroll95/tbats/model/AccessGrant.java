package com.jcarroll95.tbats.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * This entity represents an access grant
 */
@Entity
@Table(name = "access_grants")
public class AccessGrant {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "user_id", nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @Column(name = "resource_name", nullable = false, length = 255)
    private String resourceName;

    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @Column(nullable = false)
    private boolean revoked;

    public AccessGrant() {
        this.revoked = false;
    }

    public AccessGrant(UUID userId, String resourceName, int durationMinutes) {
        this.userId = userId;
        this.resourceName = resourceName;
        this.issuedAt = OffsetDateTime.now();
        this.expiresAt = issuedAt.plusMinutes(durationMinutes);
        this.revoked = false;
    }

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public OffsetDateTime getIssuedAt() {
        return this.issuedAt;
    }

    public OffsetDateTime getExpiresAt() {
        return this.expiresAt;
    }

    public boolean getRevoked() {
        return this.revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    protected void setUserId(UUID userId) {
        this.userId = userId;
    }

    protected void setIssuedAt(OffsetDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    protected void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

}
