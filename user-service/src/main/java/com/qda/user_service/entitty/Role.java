package com.qda.user_service.entitty;
import jakarta.persistence.Embeddable;

@Embeddable
public enum Role {
    ADMIN,
    USER
}
