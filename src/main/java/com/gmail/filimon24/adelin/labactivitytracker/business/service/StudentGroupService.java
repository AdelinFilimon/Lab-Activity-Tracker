package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper.StudentGroupMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentGroupRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.StudentGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;
    private final StudentGroupMapper studentGroupMapper;

    public void insertInitialGroups() {
        for (StudentGroupDto studentGroupDto : CustomApplicationProperties.initialGroups) {
            if (!studentGroupRepository.existsStudentGroupByGroupNumber(studentGroupDto.getGroupNumber())) {
                StudentGroup studentGroup = studentGroupMapper.dtoToDao(studentGroupDto);
                studentGroupRepository.save(studentGroup);
            }
        }
    }
}
