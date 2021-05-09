package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaboratoryClassForm {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(CustomApplicationProperties.DATE_TIME_FORMAT);

    private Long id;
    private Integer labNumber;
    private String date;
    private String title;
    private String description;

    public LaboratoryClassDto toLaboratoryClassDto() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(CustomApplicationProperties.DATE_TIME_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(date);
        return LaboratoryClassDto.builder()
                .id(id)
                .labNumber(labNumber)
                .date(dateTime)
                .title(title)
                .description(description)
                .build();
    }

    public static LaboratoryClassForm fromLaboratoryClassDto(LaboratoryClassDto laboratoryClassDto) {
        return LaboratoryClassForm.builder()
                .id(laboratoryClassDto.getId())
                .labNumber(laboratoryClassDto.getLabNumber())
                .date(laboratoryClassDto.getDate().toString(dateTimeFormatter))
                .title(laboratoryClassDto.getTitle())
                .description(laboratoryClassDto.getDescription())
                .build();
    }
}
