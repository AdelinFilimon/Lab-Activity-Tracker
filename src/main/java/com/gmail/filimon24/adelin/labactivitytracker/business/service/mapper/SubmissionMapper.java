package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.SubmissionDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Submission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SubmissionMapper implements Mapper<Submission, SubmissionDto> {

    private final AssignmentMapper assignmentMapper;

    @Override
    public SubmissionDto daoToDto(Submission submission) {
        return SubmissionDto.builder()
                .repositoryLink(submission.getRepositoryLink())
                .comment(submission.getComment())
                .assignment(assignmentMapper.daoToDto(submission.getAssignment()))
                .build();
    }

    @Override
    public Submission dtoToDao(SubmissionDto submissionDto) {
        return Submission.builder()
                .repositoryLink(submissionDto.getRepositoryLink())
                .comment(submissionDto.getComment())
                .assignment(assignmentMapper.dtoToDao(submissionDto.getAssignment()))
                .build();
    }
}
