package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.StudentNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.StudentService;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.TokenService;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.StudentCreationForm;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.StudentUpdateForm;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final TokenService tokenService;

    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody StudentCreationForm studentCF) {
        try {
            StudentDto student = (StudentDto) studentService.create(studentCF.toStudentDto());
            tokenService.create(student);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        try {
            return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(studentService.get(id), HttpStatus.OK);
        }
        catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteStudents() {
        try {
            studentService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateForm studentUF) {
        try {
            studentService.update(id, studentUF.toStudentDto());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (InvalidFieldException e) {
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
