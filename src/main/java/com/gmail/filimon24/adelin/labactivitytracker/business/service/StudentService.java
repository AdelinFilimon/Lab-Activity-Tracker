package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.FieldType;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper.StudentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.EmailValidator;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.GroupValidator;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.PasswordValidator;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentGroupRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;

    private final PasswordValidator passwordValidator;
    private final GroupValidator groupValidator;
    private final EmailValidator emailValidator;
    private final PasswordEncoder passwordEncoder;

    private final StudentMapper studentMapper;

    public StudentDto createStudent(StudentDto studentDto) {

        String email = studentDto.getEmail();
        String password = studentDto.getPassword();
        Long groupNumber = studentDto.getStudentGroup().getGroupNumber();

        if (!passwordValidator.isValid(password))
            throw new InvalidFieldException(FieldType.PASSWORD, password);

        if (!groupValidator.isValid(groupNumber))
            throw new InvalidFieldException(FieldType.STUDENT_GROUP, groupNumber.toString());

        if (!emailValidator.isValid(email))
            throw new InvalidFieldException(FieldType.EMAIL, email);

        studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));

        Long studentGroupId = studentGroupRepository.findStudentGroupByGroupNumber(groupNumber).getId();
        studentDto.getStudentGroup().setId(studentGroupId);
        Student student = studentRepository.save(studentMapper.dtoToDao(studentDto));
        studentDto.setId(student.getId());

        return studentDto;
    }




}
