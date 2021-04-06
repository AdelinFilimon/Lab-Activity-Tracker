package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AttendanceType {
    PRESENT("P"), ABSENT("A"), LATE("L");

    private final String code;
}
