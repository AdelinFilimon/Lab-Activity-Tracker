package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentMapper implements Mapper<Student, StudentDto> {

    private final StudentGroupMapper studentGroupMapper;

    @Override
    public StudentDto daoToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .password(student.getPassword())
                .email(student.getEmail())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .hobby(student.getHobby())
                .studentGroup(studentGroupMapper.daoToDto(student.getStudentGroup()))
                .build();
    }

}
