package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RequiredArgsConstructor
@Component
public class JsonMapperFactoryImp implements JsonMapperFactory {

    private final JsonMapper<StudentGroupDto> studentGroupMapper;
    private final JsonMapper<TeacherDto> teacherMapper;

    @Override
    public JsonMapper<?> obtainMapper(String mapperType) {
        switch (mapperType) {
            case "teachers":
                return teacherMapper;
            case "studentGroups":
                return studentGroupMapper;
            default:
                throw new NotImplementedException();
        }
    }
}
