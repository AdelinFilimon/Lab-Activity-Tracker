package com.gmail.filimon24.adelin.labactivitytracker.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryClassDto {
    private Long id;
    private Integer labNumber;

    @DateTimeFormat(pattern = CustomApplicationProperties.dateTimeFormat)
    private DateTime date;

    private String title;
    private String description;
}
