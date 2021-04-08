package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;

public class LaboratoryClassMapper implements Mapper<LaboratoryClass, LaboratoryClassDto> {

    @Override
    public LaboratoryClassDto daoToDto(LaboratoryClass laboratoryClass) {
        return LaboratoryClassDto.builder()
                .id(laboratoryClass.getId())
                .labNumber(laboratoryClass.getLabNumber())
                .date(laboratoryClass.getDate())
                .title(laboratoryClass.getTitle())
                .description(laboratoryClass.getDescription())
                .build();
    }

}
