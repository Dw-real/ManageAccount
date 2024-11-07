package com.dw.account.domain.exception;

public class InfoNotFoundException extends RuntimeException{
    public InfoNotFoundException(String message) {
        super(message);
    }
}
