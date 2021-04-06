package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AttendanceId implements Serializable {

    private Long student;
    private Long laboratoryClass;

}
