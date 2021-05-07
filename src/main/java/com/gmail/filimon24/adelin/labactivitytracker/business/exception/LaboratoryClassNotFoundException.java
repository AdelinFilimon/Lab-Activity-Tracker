package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class LaboratoryClassNotFoundException extends RuntimeException {

    public LaboratoryClassNotFoundException(Long id) {
        super("Laboratory class with id " + id + " not found");
    }
    public LaboratoryClassNotFoundException() {super();}
}
