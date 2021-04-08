package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class AssignmentDto {
    private Long id;
    private String name;
    private DateTime deadline;
    private String description;
    private LaboratoryClassDto laboratoryClass;
}
