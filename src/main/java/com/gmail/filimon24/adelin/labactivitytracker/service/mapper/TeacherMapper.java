package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Teacher;

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

}
