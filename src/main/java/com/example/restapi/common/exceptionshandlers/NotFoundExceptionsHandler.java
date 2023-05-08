package com.example.restapi.common.exceptionshandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.restapi.common.exceptions.NotfoundException;

@ControllerAdvice
public class NotFoundExceptionsHandler {
    
    @ExceptionHandler(value = {NotfoundException.class})
    protected ResponseEntity<?> handleConflict(NotfoundException ex, WebRequest request) {

        return ResponseEntity.status(404).body("");
    }
}
