package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super ("Username " + username + " already exists");
    }
}
