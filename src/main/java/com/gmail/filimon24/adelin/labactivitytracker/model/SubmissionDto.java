package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDto {
    private String repositoryLink;
    private String comment;
    private StudentDto student;
    private AssignmentDto assignment;
    private Integer grade;
}
