package org.soneech.truereview.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final Long userId;

    public UserNotFoundException(String message, Long userId) {
        super(message);
        this.userId = userId;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
