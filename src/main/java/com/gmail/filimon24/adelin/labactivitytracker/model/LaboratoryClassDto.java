package com.gmail.filimon24.adelin.labactivitytracker.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
public class LaboratoryClassDto {
    private Long id;
    private Integer labNumber;

    @DateTimeFormat(pattern = CustomApplicationProperties.dateTimeFormat)
    private DateTime date;

    private String title;
    private String description;
}
