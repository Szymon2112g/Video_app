package com.wideoapp.WideoAppDatabase.Web.controller.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {

        String bodyOfResponse = "something went wrong";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        LOGGER.error("Invalid Input Exception: " + exception.getMessage());
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { NoFoundObjectException.class })
    public ResponseEntity<Object> handleNoFoundObjectException(NoFoundObjectException exception) {
        LOGGER.error("No Found Object Exception: " + exception.getMessage());
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
