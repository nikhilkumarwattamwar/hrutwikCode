package com.loanapp.loanManagementSystem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
        ErrorDetails details= new ErrorDetails(HttpStatus.NOT_FOUND,e.getMessage(), request.getDescription(false), LocalDateTime.now());
        log.warn("ResourceNotFound : "+e.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException e, WebRequest request){
        ErrorDetails details= new ErrorDetails(HttpStatus.BAD_REQUEST,e.getMessage(), request.getDescription(false), LocalDateTime.now());
        log.warn("BadRequest : "+e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e, WebRequest request){
        log.error("Unexpected error : ", e);
        ErrorDetails details= new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),request.getDescription(false),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
    }
}
