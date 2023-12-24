package com.blog.exceptions;

import com.blog.responses.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> HandleUserException(UserException ex, WebRequest request){

        ErrorResponse errorMessage = new ErrorResponse(new Date(),ex.getMessage());

        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<Object> HandleOthersException(Exception ex,WebRequest request){

        ErrorResponse errorMessage=new ErrorResponse(new Date(), ex.getMessage());

        return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
