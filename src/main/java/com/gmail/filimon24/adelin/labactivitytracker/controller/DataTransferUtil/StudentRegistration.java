package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import lombok.Data;

@Data
public class StudentRegistration {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String hobby;
    private Long groupNumber;
    private String token;

    public StudentDto getStudent() {
        return StudentDto.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .hobby(hobby)
                .studentGroup(StudentGroupDto.builder().groupNumber(groupNumber).build())
                .build();
    }

    public TokenDto getToken() {
        return TokenDto.builder()
                .token(token)
                .build();
    }
    
}
