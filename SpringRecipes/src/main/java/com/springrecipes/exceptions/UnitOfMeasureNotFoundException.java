package com.springrecipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnitOfMeasureNotFoundException extends RuntimeException {

    public UnitOfMeasureNotFoundException(String exception) {
        super(exception);
    }
}
