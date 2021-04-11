package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.EmailAlreadyUsed;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.FieldType;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.StudentService;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.TokenService;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.TokenValidator;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.StudentRegistration;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;
    private final TokenValidator tokenValidator;
    private final TokenService tokenService;

    @PostMapping("/register-student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistration studentRegistration) {
        try {
            String token = studentRegistration.getToken().getToken();

            if(!tokenValidator.isValid(token))
                throw new InvalidFieldException(FieldType.TOKEN, token);

            StudentDto student = studentService.createStudent(studentRegistration.getStudent());
            tokenService.deleteToken(token);
            return new ResponseEntity<>(student, HttpStatus.OK);

        }
        catch (EmailAlreadyUsed e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
