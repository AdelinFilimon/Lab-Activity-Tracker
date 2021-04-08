package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "student", referencedColumnName = "student_id", insertable = false, updatable = false),
            @JoinColumn(name = "assignment", referencedColumnName = "assignment_id", insertable = false, updatable = false)
    })
    private Submission submission;

    @Column
    private Integer grade;

}
