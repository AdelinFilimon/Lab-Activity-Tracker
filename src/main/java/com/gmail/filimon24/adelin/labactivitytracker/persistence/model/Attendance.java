package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(AttendanceId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private LaboratoryClass laboratoryClass;

    @Column(nullable = false)
    private AttendanceType attendance;

}
