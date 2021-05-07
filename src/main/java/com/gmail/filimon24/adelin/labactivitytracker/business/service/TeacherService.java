package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.FieldType;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.TeacherMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.EmailValidator;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.PasswordValidator;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.UsernameValidator;
import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TeacherRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService implements CreateEntityService {

    private final EmailValidator emailValidator;
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    public Object create(Object teacher) {
        TeacherDto teacherDto = (TeacherDto) teacher;

        String email = teacherDto.getEmail();
        String username = teacherDto.getUsername();
        String password = teacherDto.getPassword();

        if (!passwordValidator.isValid(teacherDto.getPassword()))
            throw new InvalidFieldException(FieldType.PASSWORD, password);

        if (!usernameValidator.isValid(username))
            throw new InvalidFieldException(FieldType.USERNAME, username);

        if (!emailValidator.isValid(email))
            throw new InvalidFieldException(FieldType.EMAIL, email);

        teacherDto.setPassword(passwordEncoder.encode(password));

        Teacher teacherDao = teacherRepository.save(teacherMapper.dataAccessToEntity(teacherDto));
        return teacherMapper.entityToDataAccess(teacherDao);
    }

}
