package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class AttendanceDto {
    private StudentDto student;
    private LaboratoryClassDto laboratoryClass;
    private String attendance;
}
