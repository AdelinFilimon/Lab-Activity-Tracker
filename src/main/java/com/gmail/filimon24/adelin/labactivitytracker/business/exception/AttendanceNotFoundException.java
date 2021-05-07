package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceId;

public class AttendanceNotFoundException extends RuntimeException {

    public AttendanceNotFoundException(AttendanceId id) {
        super("Attendance with id " + id.toString() + " not found");
    }
}
