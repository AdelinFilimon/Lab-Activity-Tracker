package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super("Student with id " + id + " not found");
    }
    public StudentNotFoundException() {
        super("Student not found");
    }

}
