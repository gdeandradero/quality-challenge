package com.meli.qualitychallenge.exceptions;

import com.meli.qualitychallenge.exceptions.models.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class APIExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String cause = ex.getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new ValidationError(cause, status), status);
    }

    @ExceptionHandler
    public ResponseEntity<Object> districtError(DistrictException ex) {
        return new ResponseEntity<>(new ValidationError("District not found", HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

}
