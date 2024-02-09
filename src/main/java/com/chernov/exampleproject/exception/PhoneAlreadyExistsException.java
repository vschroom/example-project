package com.chernov.exampleproject.exception;

import static java.lang.String.format;

public class PhoneAlreadyExistsException extends RuntimeException {

    private static final String DEFAULT_MSG = "Телефон %s уже существует.";

    public PhoneAlreadyExistsException(String phone) {
        super(format(DEFAULT_MSG, phone));
    }
}
