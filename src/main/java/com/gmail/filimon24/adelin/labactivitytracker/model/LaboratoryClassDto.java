package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@Builder
public class LaboratoryClassDto {
    private Long id;
    private Integer labNumber;
    private DateTime date;
    private String title;
    private String description;
}
