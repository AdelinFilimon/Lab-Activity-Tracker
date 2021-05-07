package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import lombok.Data;

@Data
public class StudentCreationForm {
    private String email;
    private String firstName;
    private String lastName;
    private Long groupNumber;

    public StudentDto toStudentDto() {
        return StudentDto.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .studentGroup(StudentGroupDto.builder().groupNumber(groupNumber).build())
                .build();
    }

}
