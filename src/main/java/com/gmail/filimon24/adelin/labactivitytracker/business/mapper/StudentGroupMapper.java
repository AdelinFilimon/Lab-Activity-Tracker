package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.StudentGroup;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class StudentGroupMapper implements ModelMapper<StudentGroup, StudentGroupDto>, JsonMapper<StudentGroupDto> {

    @Override
    public StudentGroupDto entityToDataAccess(StudentGroup studentGroup) {
        return StudentGroupDto.builder()
                .id(studentGroup.getId())
                .groupNumber(studentGroup.getGroupNumber())
                .build();
    }

    @Override
    public StudentGroup dataAccessToEntity(StudentGroupDto studentGroupDto) {
        return StudentGroup.builder()
                .id(studentGroupDto.getId())
                .groupNumber(studentGroupDto.getGroupNumber())
                .build();
    }

    @Override
    public StudentGroupDto fromJson(JSONObject jsonObject) {
        return StudentGroupDto.builder()
                .groupNumber(jsonObject.getLong("studentGroup"))
                .build();
    }
}
