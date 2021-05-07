package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceId;

public class AttendanceAlreadyExistsException extends RuntimeException{

    public AttendanceAlreadyExistsException(AttendanceId attendanceId) {
        super("Attendance with id " + attendanceId.toString() + " already exists");
    }
}
