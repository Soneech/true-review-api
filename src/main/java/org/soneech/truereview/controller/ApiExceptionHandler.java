package org.soneech.truereview.controller;

import org.soneech.truereview.dto.response.BadCredentialsResponse;
import org.soneech.truereview.exception.AuthException;
import org.soneech.truereview.exception.RegistrationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BadCredentialsResponse> handleAuthException(AuthException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new BadCredentialsResponse(
                        exception.getHttpStatus().toString(), exception.getMessage(),
                        exception.getCredentialsErrors()
                ));
    }

    @ExceptionHandler
    public ResponseEntity<BadCredentialsResponse> handleRegistrationException(RegistrationException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new BadCredentialsResponse(
                        exception.getHttpStatus().toString(), exception.getMessage(),
                        exception.getCredentialsErrors())
                );
    }
}
