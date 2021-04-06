package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long groupNumber;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    List<Student> students;

}
