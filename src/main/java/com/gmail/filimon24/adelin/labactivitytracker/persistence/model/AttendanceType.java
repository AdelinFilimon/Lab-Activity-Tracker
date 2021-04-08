package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AttendanceType {
    PRESENT("PRESENT"), ABSENT("ABSENT"), LATE("LATE");

    private final String code;
}
