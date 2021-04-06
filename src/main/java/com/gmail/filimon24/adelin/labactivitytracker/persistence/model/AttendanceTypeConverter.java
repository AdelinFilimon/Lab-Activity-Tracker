package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AttendanceTypeConverter implements AttributeConverter<AttendanceType, String> {

    @Override
    public String convertToDatabaseColumn(AttendanceType attendanceType) {
        if (attendanceType == null)
            return null;
        return attendanceType.getCode();
    }

    @Override
    public AttendanceType convertToEntityAttribute(String s) {
        if (s == null)
            return null;
        return Stream.of(AttendanceType.values())
                .filter(c -> c.getCode().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
