package com.dw.account.domain.exception;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
