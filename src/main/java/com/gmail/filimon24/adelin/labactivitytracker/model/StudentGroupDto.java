package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentGroupDto {
    private Long id;
    private Long groupNumber;
}
