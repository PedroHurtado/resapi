package com.example.restapi.common.exceptions;

import java.util.List;

import com.example.restapi.common.validations.ErrorReponseValidator;

public class BadRequestExceptions extends RuntimeException {
    
    private final List<ErrorReponseValidator> errors;

    public BadRequestExceptions(List<ErrorReponseValidator> errors){
        this.errors = errors;
    }
    
    public List<ErrorReponseValidator> getErrors(){
        return errors;
    }


}
