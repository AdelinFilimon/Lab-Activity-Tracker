package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.SubmissionDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Submission;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
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
        return Mapper.super.dtoToDao(submissionDto);
    }
}
