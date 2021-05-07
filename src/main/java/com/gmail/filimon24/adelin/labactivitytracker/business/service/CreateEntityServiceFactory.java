package com.gmail.filimon24.adelin.labactivitytracker.business.service;

public interface CreateEntityServiceFactory {
    CreateEntityService obtainService(String serviceType);
}
