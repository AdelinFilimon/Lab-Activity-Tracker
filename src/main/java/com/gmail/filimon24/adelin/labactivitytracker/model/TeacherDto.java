package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}