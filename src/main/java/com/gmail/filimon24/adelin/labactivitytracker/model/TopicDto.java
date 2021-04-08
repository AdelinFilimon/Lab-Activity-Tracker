package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicDto {
    private Long id;
    private String title;
    private LaboratoryClassDto laboratoryClass;
}