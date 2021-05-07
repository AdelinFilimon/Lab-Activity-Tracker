package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.FieldType;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.StudentNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.TokenNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.StudentService;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.TokenService;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.TokenValidator;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.StudentRegistrationForm;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;
    private final TokenValidator tokenValidator;
    private final TokenService tokenService;

    @PostMapping("/register-student")
    @Transactional
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationForm studentRegistration) {
        try {
            TokenDto token = studentRegistration.getToken();

            if(!tokenValidator.isValid(token))
                throw new InvalidFieldException(FieldType.TOKEN, token.getToken());

            StudentDto student = tokenService.get(token.getToken()).getStudent();

            student.setUsername(studentRegistration.getUsername());
            student.setPassword(studentRegistration.getPassword());
            student.setHobby(studentRegistration.getHobby());

            studentService.registerStudent(student);

            tokenService.delete(token.getToken());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (InvalidFieldException | TokenNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
