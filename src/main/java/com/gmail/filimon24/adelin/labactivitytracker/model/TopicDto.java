package com.gmail.filimon24.adelin.labactivitytracker.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class TopicDto {
    private Long id;
    private String title;
    private LaboratoryClassDto laboratoryClass;
}
