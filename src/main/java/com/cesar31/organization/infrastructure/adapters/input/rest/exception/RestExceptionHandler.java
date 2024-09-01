package com.cesar31.organization.infrastructure.adapters.input.rest.exception;

import com.cesar31.organization.application.exception.ApplicationException;
import com.cesar31.organization.application.exception.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleDomainEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleDomainException(ApplicationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorFieldResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        var errors = ex.getConstraintViolations()
                .stream()
                .map(error -> new ErrorField(error.getPropertyPath().toString(), error.getMessage(), error.getInvalidValue()))
                .toList();

        var response = new ErrorFieldResponse("invalid_request", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
