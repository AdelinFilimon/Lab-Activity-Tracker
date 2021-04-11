package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper.TeacherMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TeacherRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public void insertInitialTeachers() {
        for (TeacherDto teacherDto : CustomApplicationProperties.initialTeachers) {
            if (!teacherRepository.existsTeacherByEmail(teacherDto.getEmail())) {
                Teacher teacher = teacherMapper.dtoToDao(teacherDto);
                teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
                teacherRepository.save(teacher);
            }
        }
    }
}
