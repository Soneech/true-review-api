package org.soneech.truereview.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Set;

@Getter
public class RegistrationException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final Map<String, Set<String>> credentialsErrors;

    public RegistrationException(HttpStatus httpStatus,
                                 Map<String, Set<String>> credentialsErrors, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.credentialsErrors = credentialsErrors;
    }
}
