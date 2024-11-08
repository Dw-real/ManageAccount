package com.dw.account.presentation.controller;

import com.dw.account.domain.exception.*;
import com.dw.account.presentation.ErrorMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handelConstraintViolatedException(
            ConstraintViolationException ex
    ) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errors = constraintViolations.stream()
                .map(
                        constraintViolation ->
                                extractField(constraintViolation.getPropertyPath()) + ", " +
                                        constraintViolation.getMessage()
                ).toList();

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, DuplicateIdException.class, InvalidPassWordException.class})
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
           Exception ex
    ) {
        List<String> errors = new ArrayList<>();
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodEx = (MethodArgumentNotValidException) ex;
            List<FieldError> fieldErrors = methodEx.getBindingResult().getFieldErrors();
            errors = fieldErrors.stream()
                    .map(
                            fieldError ->
                                    fieldError.getField() + ", " + fieldError.getDefaultMessage()
                    ).toList();
        }
        else if (ex instanceof DuplicateIdException) {
            errors.add(ex.getMessage());
        }
        else if (ex instanceof InvalidPassWordException) {
            errors.add(ex.getMessage());
        }

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorMessage> handleLoginException(
            LoginException ex
    ) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleIdNotFoundException(
            IdNotFoundException ex
    ) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InfoNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleInfoNotFoundException(
            InfoNotFoundException ex
    ) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    private String extractField(Path path) {
        String[] splittedArray = path.toString().split("[.]");
        int lastIndex = splittedArray.length - 1;
        return splittedArray[lastIndex];
    }
}
