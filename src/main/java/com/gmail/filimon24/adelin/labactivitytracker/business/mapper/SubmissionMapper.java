package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.SubmissionDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Submission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SubmissionMapper implements ModelMapper<Submission, SubmissionDto> {

    private final AssignmentMapper assignmentMapper;
    private final StudentMapper studentMapper;

    @Override
    public SubmissionDto entityToDataAccess(Submission submission) {
        return SubmissionDto.builder()
                .repositoryLink(submission.getRepositoryLink())
                .comment(submission.getComment())
                .student(studentMapper.entityToDataAccess(submission.getStudent()))
                .assignment(assignmentMapper.entityToDataAccess(submission.getAssignment()))
                .grade(submission.getGrade())
                .build();
    }

    @Override
    public Submission dataAccessToEntity(SubmissionDto submissionDto) {
        return Submission.builder()
                .repositoryLink(submissionDto.getRepositoryLink())
                .comment(submissionDto.getComment())
                .student(studentMapper.dataAccessToEntity(submissionDto.getStudent()))
                .assignment(assignmentMapper.dataAccessToEntity(submissionDto.getAssignment()))
                .grade(submissionDto.getGrade())
                .build();
    }
}
