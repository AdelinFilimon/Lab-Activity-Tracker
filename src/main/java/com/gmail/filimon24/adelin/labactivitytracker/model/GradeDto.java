package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GradeDto {
    private Long id;
    private SubmissionDto submission;
    private Integer grade;
}
