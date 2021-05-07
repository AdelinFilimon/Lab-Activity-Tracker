package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryClassMapper implements ModelMapper<LaboratoryClass, LaboratoryClassDto> {

    @Override
    public LaboratoryClassDto entityToDataAccess(LaboratoryClass laboratoryClass) {
        return LaboratoryClassDto.builder()
                .id(laboratoryClass.getId())
                .labNumber(laboratoryClass.getLabNumber())
                .date(laboratoryClass.getDate())
                .title(laboratoryClass.getTitle())
                .description(laboratoryClass.getDescription())
                .build();
    }

    @Override
    public LaboratoryClass dataAccessToEntity(LaboratoryClassDto laboratoryClassDto) {
        return LaboratoryClass.builder()
                .id(laboratoryClassDto.getId())
                .labNumber(laboratoryClassDto.getLabNumber())
                .date(laboratoryClassDto.getDate())
                .title(laboratoryClassDto.getTitle())
                .description(laboratoryClassDto.getDescription())
                .build();
    }
}
