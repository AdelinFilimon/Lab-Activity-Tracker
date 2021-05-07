package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
@RequiredArgsConstructor
public class CreateEntityServiceFactoryImp implements CreateEntityServiceFactory {
    
    private final CreateEntityService teacherService;
    private final CreateEntityService studentGroupService;

    @Override
    public CreateEntityService obtainService(String serviceType) {
        switch (serviceType) {
            case "teachers": return teacherService;
            case "studentGroups": return studentGroupService;
            default:
                throw new NotImplementedException();
        }
    }
}
