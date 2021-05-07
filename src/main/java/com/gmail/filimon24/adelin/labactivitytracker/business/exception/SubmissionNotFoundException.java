package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.SubmissionId;

public class SubmissionNotFoundException extends RuntimeException{
    public SubmissionNotFoundException() {
        super("Submission not found");
    }
    public SubmissionNotFoundException(SubmissionId submissionId) {
        super("Submission with id " + submissionId.toString() + " not found");
    }
}
