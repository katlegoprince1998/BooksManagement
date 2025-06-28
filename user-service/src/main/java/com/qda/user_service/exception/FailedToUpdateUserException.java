package com.qda.user_service.exception;

public class FailedToUpdateUserException extends RuntimeException {
    public FailedToUpdateUserException(String message) {
        super(message);
    }
}
