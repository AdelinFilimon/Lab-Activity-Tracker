package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long groupNumber;

    @OneToMany(mappedBy = "studentGroup", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Student> students;

}
