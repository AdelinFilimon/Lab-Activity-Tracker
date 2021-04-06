package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SubmissionId.class)
public class Submission {

    @Column(nullable = false, length = 64)
    private String repositoryLink;

    @Column(length = 128)
    private String comment;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Assignment assignment;

    @OneToOne(mappedBy = "submission")
    private Grade grade;
}
