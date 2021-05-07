package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException(Long id) {
        super("Token with id " + id + " not found");
    }

    public TokenNotFoundException(String token) {super ("Token starting with " + token.substring(0, 10) + " not found");}

}
