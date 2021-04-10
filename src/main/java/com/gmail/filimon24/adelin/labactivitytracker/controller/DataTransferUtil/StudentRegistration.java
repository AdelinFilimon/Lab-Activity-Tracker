package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import lombok.Data;

@Data
public class StudentRegistration {
    private StudentDto studentDto;
    private TokenDto tokenDto;
}
