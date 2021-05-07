package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SubmissionId.class)
@Builder
public class Submission {

    @Column(nullable = false, length = CustomApplicationProperties.bigFieldLen)
    private String repositoryLink;

    @Column(length = CustomApplicationProperties.bigFieldLen)
    private String comment;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Assignment assignment;

    @Column
    private Integer grade;
}
