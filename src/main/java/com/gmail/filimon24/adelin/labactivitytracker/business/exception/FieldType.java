package com.gmail.filimon24.adelin.labactivitytracker.business.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FieldType {
    EMAIL("email"),
    STUDENT_GROUP("student group"),
    PASSWORD("password"),
    TOKEN("token"),
    LAB_NUMBER("labNumber"),
    LAB_TITLE("labTitle"),
    ID("id"),
    GRADE("grade"),
    ASSIGNMENT_NAME("assignment_name"),
    USERNAME("username");

    private final String code;
}
