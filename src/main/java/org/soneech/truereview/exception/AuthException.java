package org.soneech.truereview.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Set;

@Getter
public class AuthException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final Map<String, Set<String>> credentialsErrors;

    public AuthException(HttpStatus httpStatus, Map<String, Set<String>> credentialsErrors, String message) {
        super(message);
        this.credentialsErrors = credentialsErrors;
        this.httpStatus = httpStatus;
    }
}
