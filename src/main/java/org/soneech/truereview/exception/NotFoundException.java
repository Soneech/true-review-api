package org.soneech.truereview.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class NotFoundException extends RuntimeException {

    protected final HttpStatus httpStatus;

    protected final Long id;

    public NotFoundException(String message, HttpStatus httpStatus, Long id) {
        super(message);
        this.httpStatus = httpStatus;
        this.id = id;
    }
}
