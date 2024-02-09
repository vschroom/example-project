package com.chernov.exampleproject.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super(String.format("Account with id %s not fount", id));
    }
}
