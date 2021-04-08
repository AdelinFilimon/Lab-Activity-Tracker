package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AssignmentMapper implements Mapper<Assignment, AssignmentDto> {

    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public AssignmentDto daoToDto(Assignment assignment) {
        return AssignmentDto.builder()
                .id(assignment.getId())
                .name(assignment.getName())
                .deadline(assignment.getDeadline())
                .description(assignment.getDescription())
                .laboratoryClass(laboratoryClassMapper.daoToDto(assignment.getLaboratoryClass()))
                .build();
    }

    @Override
    public Assignment dtoToDao(AssignmentDto assignmentDto) {
        return Assignment.builder()
                .id(assignmentDto.getId())
                .name(assignmentDto.getName())
                .deadline(assignmentDto.getDeadline())
                .description(assignmentDto.getDescription())
                .laboratoryClass(laboratoryClassMapper.dtoToDao(assignmentDto.getLaboratoryClass()))
                .build();
    }
}
