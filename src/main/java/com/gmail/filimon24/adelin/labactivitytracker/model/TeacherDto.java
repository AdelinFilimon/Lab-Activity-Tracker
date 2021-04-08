package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class TeacherDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
