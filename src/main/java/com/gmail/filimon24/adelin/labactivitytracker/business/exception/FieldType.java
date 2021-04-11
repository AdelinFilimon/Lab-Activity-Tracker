package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FieldType {
    EMAIL("email"), STUDENT_GROUP("student group"), PASSWORD("password"), TOKEN("token");

    private final String code;
}
