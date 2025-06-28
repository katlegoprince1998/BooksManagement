package com.qda.user_service.exception;

public class FailedToCreatUserException extends RuntimeException {
    public FailedToCreatUserException(String message) {
        super(message);
    }
}
