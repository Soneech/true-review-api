package org.soneech.truereview.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.Set;

@Getter
public class UpdateCategoryException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final Map<String, Set<String>> fieldsErrors;

    public UpdateCategoryException(HttpStatus httpStatus, Map<String, Set<String>> fieldsErrors, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.fieldsErrors = fieldsErrors;
    }
}
