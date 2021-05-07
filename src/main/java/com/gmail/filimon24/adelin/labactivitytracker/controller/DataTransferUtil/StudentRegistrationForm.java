package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import lombok.Data;

@Data
public class StudentRegistrationForm {
    private String username;
    private String password;
    private String hobby;
    private String token;

    public StudentDto getStudent() {
        return StudentDto.builder()
                .username(username)
                .password(password)
                .hobby(hobby)
                .build();
    }

    public TokenDto getToken() {
        return TokenDto.builder()
                .token(token)
                .build();
    }
    
}
