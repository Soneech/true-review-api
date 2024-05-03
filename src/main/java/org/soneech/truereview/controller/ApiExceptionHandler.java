package org.soneech.truereview.controller;

import org.soneech.truereview.dto.response.errors.BadCredentialsResponse;
import org.soneech.truereview.dto.response.errors.BadRequestResponse;
import org.soneech.truereview.dto.response.errors.NotFoundResponse;
import org.soneech.truereview.exception.*;
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
                        exception.getCredentialsErrors()));
    }

    @ExceptionHandler
    public ResponseEntity<NotFoundResponse> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new NotFoundResponse(exception.getHttpStatus().toString(),
                        exception.getMessage(), exception.getId()));
    }

    @ExceptionHandler
    public ResponseEntity<BadRequestResponse> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new BadRequestResponse(exception.getHttpStatus().toString(), exception.getMessage()));
    }
}
