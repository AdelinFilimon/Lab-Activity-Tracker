package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Teacher;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper implements ModelMapper<Teacher, TeacherDto>, JsonMapper<TeacherDto> {

    @Override
    public TeacherDto entityToDataAccess(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .username(teacher.getUsername())
                .email(teacher.getEmail())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .password(teacher.getPassword())
                .build();
    }

    @Override
    public Teacher dataAccessToEntity(TeacherDto teacherDto) {
        return Teacher.builder()
                .id(teacherDto.getId())
                .username(teacherDto.getUsername())
                .email(teacherDto.getEmail())
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .password(teacherDto.getPassword())
                .build();
    }

    @Override
    public TeacherDto fromJson(JSONObject jsonObject) {
        return TeacherDto.builder()
                .firstName((String) jsonObject.get("firstName"))
                .lastName((String) jsonObject.get("lastName"))
                .username((String) jsonObject.get("username"))
                .email((String) jsonObject.get("email"))
                .password((String) jsonObject.get("password"))
                .build();
    }

}
