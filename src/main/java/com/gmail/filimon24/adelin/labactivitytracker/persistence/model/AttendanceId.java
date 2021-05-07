package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AttendanceId implements Serializable {

    private Long student;
    private Long laboratoryClass;

}
