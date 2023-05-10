package com.example.restapi.common.exceptionshandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.restapi.common.exceptions.BadRequestExceptions;

@ControllerAdvice
public class BadRequestEcectionsHandler {

    @ExceptionHandler({BadRequestExceptions.class})
    protected ResponseEntity<?> handleConflict(BadRequestExceptions ex, WebRequest request) {

        return ResponseEntity.status(400).body(ex.getErrors());
    }
}
