package com.gmail.filimon24.adelin.labactivitytracker.business.validator;

public interface Validator<T> {
    Boolean isValid(T t);
}
