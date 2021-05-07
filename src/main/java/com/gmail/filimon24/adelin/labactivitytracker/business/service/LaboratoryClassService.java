package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.FieldType;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.LaboratoryClassNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.LaboratoryClassMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.LaboratoryClassRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaboratoryClassService implements BasicService<LaboratoryClassDto, Long>, UpdateEntityService<LaboratoryClassDto, Long> {

    private final LaboratoryClassRepository laboratoryClassRepository;
    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public Object create(Object laboratoryClass) {
        LaboratoryClass labClassDao = laboratoryClassRepository.save(laboratoryClassMapper.dataAccessToEntity((LaboratoryClassDto) laboratoryClass));
        return laboratoryClassMapper.entityToDataAccess(labClassDao);
    }

    @Override
    public List<LaboratoryClassDto> getAll() {
        return laboratoryClassRepository.findAll()
                .stream()
                .map(laboratoryClassMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

    @Override
    public LaboratoryClassDto get(Long id) {
        LaboratoryClass laboratoryClass = laboratoryClassRepository.findById(id)
                .orElseThrow(() -> new LaboratoryClassNotFoundException(id));
        return laboratoryClassMapper.entityToDataAccess(laboratoryClass);
    }

    @Override
    public void deleteAll() {
        laboratoryClassRepository.deleteAll();
    }

    @Override
    public void delete(Long id) {
        if (!laboratoryClassRepository.existsById(id))
            throw new LaboratoryClassNotFoundException(id);
        laboratoryClassRepository.deleteById(id);
    }

    @Override
    public void update(Long id, LaboratoryClassDto laboratoryClassDto) {
        Integer labNumber = laboratoryClassDto.getLabNumber();
        DateTime dateTime = laboratoryClassDto.getDate();
        String title = laboratoryClassDto.getTitle();
        String description = laboratoryClassDto.getDescription();

        LaboratoryClass laboratoryClass = laboratoryClassRepository.findById(id)
                .orElseThrow(() -> new LaboratoryClassNotFoundException(id));

        if (labNumber != null) {
            if (laboratoryClassRepository.existsByLabNumber(labNumber))
                throw new InvalidFieldException(FieldType.LAB_NUMBER, labNumber.toString());
            laboratoryClass.setLabNumber(labNumber);
        }

        if (dateTime != null) {
            laboratoryClass.setDate(dateTime);
        }

        if (title != null) {
            laboratoryClass.setTitle(title);
        }

        if (description != null) {
            laboratoryClass.setDescription(description);
        }

        laboratoryClassRepository.save(laboratoryClass);
    }
}
