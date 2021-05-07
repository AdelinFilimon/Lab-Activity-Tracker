package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateForm {
    private String username;
    private String password;
    private String hobby;
    private String email;
    private String firstName;
    private String lastName;
    private Long groupNumber;

    public StudentDto toStudentDto() {
        return StudentDto.builder()
                .username(username)
                .password(password)
                .hobby(hobby)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .studentGroup(StudentGroupDto.builder().groupNumber(groupNumber).build())
                .build();
    }
}
