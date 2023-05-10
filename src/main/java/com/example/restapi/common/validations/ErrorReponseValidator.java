package com.example.restapi.common.validations;

public record ErrorReponseValidator(String message, String filed, Object attemptedValue, String Code) {

}
