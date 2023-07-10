package com.searchtopic.topicpedia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler(ResourceEmptyException.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest wbreq){
         ExceptionResponse exceptionResponse = new ExceptionResponse(
                 new Date(), ex.getMessage(), wbreq.getDescription(false)
         );
         return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
