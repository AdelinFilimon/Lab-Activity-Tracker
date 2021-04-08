package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(AttendanceId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private LaboratoryClass laboratoryClass;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('PRESENT', 'ABSENT', 'LATE')", nullable = false)
    private AttendanceType attendance;

}
