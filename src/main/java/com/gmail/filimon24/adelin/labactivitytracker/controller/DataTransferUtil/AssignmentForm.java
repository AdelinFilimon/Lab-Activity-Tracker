package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Data
@Builder
public class AssignmentForm {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(CustomApplicationProperties.DATE_TIME_FORMAT);
    private Long id;
    private String name;
    private String deadline;
    private String description;
    private Integer labNumber;
    private Long labId;
    private String labTitle;

    public AssignmentDto toAssignmentDto() {
        DateTime dateTime = dateTimeFormatter.parseDateTime(deadline);
        return AssignmentDto.builder()
                .id(id)
                .name(name)
                .deadline(dateTime)
                .description(description)
                .laboratoryClass(LaboratoryClassDto.builder()
                        .id(labId)
                        .labNumber(labNumber)
                        .title(labTitle)
                        .build())
                .build();
    }

    public static AssignmentForm fromAssignmentDto(AssignmentDto assignmentDto) {
        return AssignmentForm.builder()
                .id(assignmentDto.getId())
                .name(assignmentDto.getName())
                .deadline(assignmentDto.getDeadline().toString(dateTimeFormatter))
                .description(assignmentDto.getDescription())
                .labId(assignmentDto.getLaboratoryClass().getId())
                .labNumber(assignmentDto.getLaboratoryClass().getLabNumber())
                .labTitle(assignmentDto.getLaboratoryClass().getTitle())
                .build();
    }

}
