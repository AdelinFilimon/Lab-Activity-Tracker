package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.StudentGroup;

public class StudentGroupMapper implements Mapper<StudentGroup, StudentGroupDto> {

    @Override
    public StudentGroupDto daoToDto(StudentGroup studentGroup) {
        return StudentGroupDto.builder()
                .id(studentGroup.getId())
                .groupNumber(studentGroup.getGroupNumber())
                .build();
    }
}
