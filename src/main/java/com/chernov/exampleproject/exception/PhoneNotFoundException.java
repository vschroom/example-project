package com.chernov.exampleproject.exception;

import static java.lang.String.format;

public class PhoneNotFoundException extends RuntimeException {

    private static final String DEFAULT_MSG = "Phone data with id=%s not found";

    public PhoneNotFoundException(Long phoneId) {
        super(format(DEFAULT_MSG, phoneId));
    }
}
