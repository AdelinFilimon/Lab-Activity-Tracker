package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class AssignmentNotFoundException extends RuntimeException{
    public AssignmentNotFoundException(Long id) {
        super("Assignment with id " + id + " not found");
    }
}
