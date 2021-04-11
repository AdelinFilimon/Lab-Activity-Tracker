package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class EmailAlreadyUsed extends RuntimeException {

    public EmailAlreadyUsed(String email) {
        super("Email already used: " + email);
    }
}
