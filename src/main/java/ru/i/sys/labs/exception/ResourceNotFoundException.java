package ru.i.sys.labs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourceNotFoundException extends Exception {

    static final long serialVersionUID = 3L;

    public ResourceNotFoundException(String error) {

        super(error);
    }
}
