package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.FieldType;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.SubmissionNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.finder.EntityFromDtoFinder;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.AssignmentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.StudentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.SubmissionMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.SubmissionDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.SubmissionRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Submission;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.SubmissionId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService implements BasicService<SubmissionDto, SubmissionId>, UpdateEntityService<SubmissionDto, SubmissionId> {

    private final SubmissionRepository submissionRepository;
    private final StudentMapper studentMapper;
    private final EntityFromDtoFinder entityFinder;
    private final AssignmentMapper assignmentMapper;
    private final SubmissionMapper submissionMapper;

    @Override
    public Object create(Object submission) {
        SubmissionDto submissionDto = (SubmissionDto) submission;
        StudentDto studentDto = studentMapper.entityToDataAccess(entityFinder.findStudent(submissionDto.getStudent()));
        AssignmentDto assignmentDto = assignmentMapper.entityToDataAccess(entityFinder.findAssignment(submissionDto.getAssignment()));
        submissionDto.setStudent(studentDto);
        submissionDto.setAssignment(assignmentDto);
        Submission submissionDao = submissionRepository.save(submissionMapper.dataAccessToEntity(submissionDto));
        return submissionMapper.entityToDataAccess(submissionDao);
    }

    @Override
    public SubmissionDto get(SubmissionId submissionId) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(SubmissionNotFoundException::new);

        return submissionMapper.entityToDataAccess(submission);
    }

    @Override
    public List<SubmissionDto> getAll() {
        return submissionRepository.findAll()
                .stream()
                .map(submissionMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

    @Override
    public void update(SubmissionId submissionId, SubmissionDto submissionDto) {

        if (submissionDto.getGrade() == null)
            throw new InvalidFieldException(FieldType.GRADE, "");

        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(SubmissionNotFoundException::new);
        submission.setGrade(submissionDto.getGrade());
        submissionRepository.save(submission);
    }

    @Override
    public void delete(SubmissionId submissionId) {
        if (!submissionRepository.existsById(submissionId)) throw new SubmissionNotFoundException(submissionId);
        submissionRepository.deleteById(submissionId);
    }

    @Override
    public void deleteAll() {
        submissionRepository.deleteAll();
    }
}
