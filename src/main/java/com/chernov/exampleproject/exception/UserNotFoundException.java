package com.chernov.exampleproject.exception;

import static java.lang.String.format;

public class UserNotFoundException extends RuntimeException {

    private static final String DEFAULT_MSG = "Пользователь с id = %s не найден.";

    public UserNotFoundException(Long id) {
        super(format(DEFAULT_MSG, id));
    }

    public UserNotFoundException() {
    }
}
