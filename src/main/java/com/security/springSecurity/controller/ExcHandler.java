package com.security.springSecurity.controller;

import com.security.springSecurity.errorBean.CustomException;
import com.security.springSecurity.errorBean.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> errorResponse(CustomException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatus(), "Unauthorised User");
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialResponse(BadCredentialsException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Bad Credentials");
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
