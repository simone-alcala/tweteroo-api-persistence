package com.tweteroo.api.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tweteroo.api.service.exceptions.EntityNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
  
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<StandardError> entityNotFound (EntityNotFoundException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    err.setError("Resource not found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.BAD_REQUEST.value());
    err.setError("Bad Request");
       
    String errorMessage = "";
    Object[] errorObjects = e.getDetailMessageArguments();

    for (int i = 1; i < errorObjects.length; i++) {     
        errorMessage += errorObjects[i];      
    }

    if (errorMessage.isEmpty()) {
      err.setMessage(e.getMessage());
    } else {
      err.setMessage(errorMessage);  
    }
    
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<StandardError> handle(Exception e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    err.setError("Internal Server Error");
    err.setPath(request.getRequestURI());
    err.setMessage(e.getClass().getSimpleName());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);     
  }

}
