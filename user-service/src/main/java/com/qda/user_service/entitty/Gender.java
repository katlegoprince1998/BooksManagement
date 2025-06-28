package com.qda.user_service.entitty;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Gender {
    MALE,
    FEMALE,
    OTHER
}
