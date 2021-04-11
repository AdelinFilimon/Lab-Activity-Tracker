package com.gmail.filimon24.adelin.labactivitytracker.business.validator;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupValidator implements Validator<Long>{

    private final StudentGroupRepository studentGroupRepository;

    @Override
    public Boolean isValid(Long groupNumber) {

        if (groupNumber.toString().length() != CustomApplicationProperties.groupNrLen)
            return false;

        return studentGroupRepository.existsStudentGroupByGroupNumber(groupNumber);
    }
}
