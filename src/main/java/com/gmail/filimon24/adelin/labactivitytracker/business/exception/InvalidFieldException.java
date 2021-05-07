package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(FieldType fieldType, String field) {
        super(fieldType.getCode() + field + " is not valid");
    }
}
