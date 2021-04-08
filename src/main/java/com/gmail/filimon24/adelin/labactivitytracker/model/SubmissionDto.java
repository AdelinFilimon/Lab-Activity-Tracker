package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubmissionDto {
    private String repositoryLink;
    private String comment;
    private StudentDto student;
    private AssignmentDto assignment;
}
