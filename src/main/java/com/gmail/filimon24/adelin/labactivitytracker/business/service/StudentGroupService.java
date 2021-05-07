package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.StudentGroupMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentGroupRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.StudentGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentGroupService implements CreateEntityService {

    private final StudentGroupRepository studentGroupRepository;
    private final StudentGroupMapper studentGroupMapper;

    @Override
    public Object create(Object studentGroup) {
        StudentGroup studentGroupDao = studentGroupRepository.save(studentGroupMapper.dataAccessToEntity((StudentGroupDto) studentGroup));
        return studentGroupMapper.entityToDataAccess(studentGroupDao);
    }

}
