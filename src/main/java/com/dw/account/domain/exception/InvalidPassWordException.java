package com.dw.account.domain.exception;

public class InvalidPassWordException extends RuntimeException {
    public InvalidPassWordException(String message) {
        super(message);
    }
}
