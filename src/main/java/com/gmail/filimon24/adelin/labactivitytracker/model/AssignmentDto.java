package com.gmail.filimon24.adelin.labactivitytracker.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
public class AssignmentDto {
    private Long id;
    private String name;

    @DateTimeFormat(pattern = CustomApplicationProperties.dateTimeFormat)
    private DateTime deadline;

    private String description;
    private LaboratoryClassDto laboratoryClass;
}
