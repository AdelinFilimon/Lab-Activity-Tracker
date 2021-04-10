package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.service.AuthService;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.StudentRegistration;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register-student")
    public StudentDto registerStudent(@RequestBody StudentRegistration studentRegistration) {
        return authService.registerStudent(studentRegistration.getStudentDto(), studentRegistration.getTokenDto());
    }

}
