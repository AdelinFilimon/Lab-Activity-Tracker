package com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
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

    @Override
    public Student dtoToDao(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .password(studentDto.getPassword())
                .email(studentDto.getEmail())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .hobby(studentDto.getHobby())
                .studentGroup(studentGroupMapper.dtoToDao(studentDto.getStudentGroup()))
                .build();
    }
}
