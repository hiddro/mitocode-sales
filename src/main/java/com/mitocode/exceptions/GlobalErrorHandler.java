package com.mitocode.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice //interceptador de exceptiones
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception exception, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(ModelNotFoundException exception, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), req.getDescription(false)); //si pongo true tiene mas detalle

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<ErrorResponse> handleNoHandleException(NoHandlerFoundException exception, WebRequest req){
//        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), req.getDescription(false));
//
//        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
//    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

//        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), message, request.getDescription(false));

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
