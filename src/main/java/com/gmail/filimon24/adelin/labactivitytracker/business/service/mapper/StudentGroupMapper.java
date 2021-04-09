package com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.StudentGroup;
import org.springframework.stereotype.Component;

@Component
public class StudentGroupMapper implements Mapper<StudentGroup, StudentGroupDto> {

    @Override
    public StudentGroupDto daoToDto(StudentGroup studentGroup) {
        return StudentGroupDto.builder()
                .id(studentGroup.getId())
                .groupNumber(studentGroup.getGroupNumber())
                .build();
    }

    @Override
    public StudentGroup dtoToDao(StudentGroupDto studentGroupDto) {
        return StudentGroup.builder()
                .id(studentGroupDto.getId())
                .groupNumber(studentGroupDto.getGroupNumber())
                .build();
    }
}
