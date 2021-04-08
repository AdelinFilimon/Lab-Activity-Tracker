package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class SubmissionDto {
    private String repositoryLink;
    private String comment;
    private StudentDto student;
    private AssignmentDto assignment;
}
