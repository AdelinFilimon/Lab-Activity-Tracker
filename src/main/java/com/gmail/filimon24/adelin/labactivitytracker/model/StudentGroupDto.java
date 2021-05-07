package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupDto {
    private Long id;
    private Long groupNumber;
}
