package com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper implements Mapper<Teacher, TeacherDto> {

    @Override
    public TeacherDto daoToDto(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .email(teacher.getEmail())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .password(teacher.getPassword())
                .build();
    }

    @Override
    public Teacher dtoToDao(TeacherDto teacherDto) {
        return Teacher.builder()
                .id(teacherDto.getId())
                .email(teacherDto.getEmail())
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .password(teacherDto.getPassword())
                .build();
    }
}
