package org.pls.democicd.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        String message = ex.getMessage();
        LOGGER.error("Error occurred: {}", ex.getMessage(), ex);
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
        String message = ex.getMessage();
        LOGGER.error("Error occurred: {}", ex.getMessage(), ex);
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(RuntimeException ex, WebRequest request) {
        String message = ex.getMessage();
        LOGGER.error("Error occurred: {}", ex.getMessage(), ex);
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        String message = "An error occurred while processing the request.";
        LOGGER.error("Error occurred: {}", ex.getMessage(), ex);
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
