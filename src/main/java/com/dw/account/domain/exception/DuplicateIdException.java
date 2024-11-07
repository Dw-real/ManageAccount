package com.dw.account.domain.exception;

public class DuplicateIdException extends RuntimeException {

    public DuplicateIdException(String message) {
        super(message);
    }
}
