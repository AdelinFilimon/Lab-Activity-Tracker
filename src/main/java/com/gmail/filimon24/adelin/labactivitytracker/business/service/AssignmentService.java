package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.AssignmentNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.finder.EntityFromDtoFinder;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.AssignmentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.LaboratoryClassMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.AssignmentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Assignment;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService implements BasicService<AssignmentDto, Long>, UpdateEntityService<AssignmentDto, Long> {

    private final AssignmentRepository assignmentRepository;
    private final EntityFromDtoFinder entityFinder;
    private final AssignmentMapper assignmentMapper;
    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public Object create(Object assignment) {
        AssignmentDto assignmentDto = (AssignmentDto) assignment;
        LaboratoryClass laboratoryClass = entityFinder.findLaboratoryClass(assignmentDto.getLaboratoryClass());
        assignmentDto.setLaboratoryClass(laboratoryClassMapper.entityToDataAccess(laboratoryClass));
        Assignment assignmentDao = assignmentRepository.save(assignmentMapper.dataAccessToEntity(assignmentDto));
        return assignmentMapper.entityToDataAccess(assignmentDao);
    }

    @Override
    public AssignmentDto get(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new AssignmentNotFoundException(id));
        return assignmentMapper.entityToDataAccess(assignment);
    }

    @Override
    public List<AssignmentDto> getAll() {
        return assignmentRepository.findAll()
                .stream()
                .map(assignmentMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, AssignmentDto assignmentDto) {

        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new AssignmentNotFoundException(id));

        LaboratoryClassDto laboratoryClassDto = assignmentDto.getLaboratoryClass();

        if (laboratoryClassDto != null) {
            assignment.setLaboratoryClass(entityFinder.findLaboratoryClass(laboratoryClassDto));
        }

        if (assignmentDto.getName() != null) {
            assignment.setName(assignmentDto.getName());
        }

        if (assignmentDto.getDeadline() != null) {
            assignment.setDeadline(assignmentDto.getDeadline());
        }

        if (assignmentDto.getDescription() != null) {
            assignment.setDescription(assignmentDto.getDescription());
        }

        assignmentRepository.save(assignment);
    }

    @Override
    public void delete(Long id) {
        if (!assignmentRepository.existsById(id)) throw new AssignmentNotFoundException(id);
        assignmentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        assignmentRepository.deleteAll();
    }

    public List<AssignmentDto> getLaboratoryClassAssignments(Long labId) {
        return assignmentRepository.findAssignmentsByLaboratoryClassId(labId)
                .stream()
                .map(assignmentMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }
}
