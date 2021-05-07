package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(Long id) {
        super("Topic with id " + id + " not found");
    }
}
