package com.artport.artport.exceptions;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private ExceptionResponse createExceptionResponse(
            HttpStatus httpStatus,
            RuntimeException e,
            WebRequest request
    ) {
		return new ExceptionResponse(
				new Date(), 
				httpStatus.value(), 
				e.getMessage(), 
				((ServletWebRequest) request).getRequest().getRequestURI()
		);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> IllegalArgumentExceptionHandler(
            WebRequest request,
            final IllegalArgumentException e
    ) {
            return new ResponseEntity<>(createExceptionResponse(HttpStatus.BAD_REQUEST, e, request), HttpStatus.BAD_REQUEST);
    }
    
}
