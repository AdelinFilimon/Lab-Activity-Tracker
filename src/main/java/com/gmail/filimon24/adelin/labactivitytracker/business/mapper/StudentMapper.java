package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentMapper implements ModelMapper<Student, StudentDto> {

    private final StudentGroupMapper studentGroupMapper;

    @Override
    public StudentDto entityToDataAccess(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .username(student.getUsername())
                .password(student.getPassword())
                .email(student.getEmail())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .hobby(student.getHobby())
                .studentGroup(studentGroupMapper.entityToDataAccess(student.getStudentGroup()))
                .build();
    }

    @Override
    public Student dataAccessToEntity(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .username(studentDto.getUsername())
                .password(studentDto.getPassword())
                .email(studentDto.getEmail())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .hobby(studentDto.getHobby())
                .studentGroup(studentGroupMapper.dataAccessToEntity(studentDto.getStudentGroup()))
                .build();
    }
}
