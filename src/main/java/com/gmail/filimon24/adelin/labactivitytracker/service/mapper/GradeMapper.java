package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.GradeDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Grade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GradeMapper implements Mapper<Grade, GradeDto> {

    private final SubmissionMapper submissionMapper;

    @Override
    public GradeDto daoToDto(Grade grade) {
        return GradeDto.builder()
                .id(grade.getId())
                .submission(submissionMapper.daoToDto(grade.getSubmission()))
                .grade(grade.getGrade())
                .build();
    }
}
