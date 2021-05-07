package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.SubmissionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionForm {
    private String repositoryLink;
    private String comment;
    private Long studentId;
    private String studentEmail;
    private String studentUsername;
    private Long assignmentId;
    private String assignmentName;
    private Integer grade;

    public SubmissionDto toSubmissionDto() {
        AssignmentDto assignmentDto = AssignmentDto.builder()
                .id(assignmentId)
                .name(assignmentName)
                .build();

        StudentDto studentDto = StudentDto.builder()
                .id(studentId)
                .username(studentUsername)
                .email(studentEmail)
                .build();

        return SubmissionDto.builder()
                .assignment(assignmentDto)
                .student(studentDto)
                .repositoryLink(repositoryLink)
                .comment(comment)
                .build();
    }

    public static SubmissionForm fromSubmissionDto(SubmissionDto submissionDto) {
        return SubmissionForm.builder()
                .assignmentId(submissionDto.getAssignment().getId())
                .assignmentName(submissionDto.getAssignment().getName())
                .studentId(submissionDto.getStudent().getId())
                .repositoryLink(submissionDto.getRepositoryLink())
                .comment(submissionDto.getComment())
                .studentEmail(submissionDto.getStudent().getEmail())
                .studentUsername(submissionDto.getStudent().getUsername())
                .grade(submissionDto.getGrade())
                .build();
    }
}
