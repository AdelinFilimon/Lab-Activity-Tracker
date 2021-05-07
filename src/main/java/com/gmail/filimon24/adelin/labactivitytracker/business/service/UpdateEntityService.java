package com.gmail.filimon24.adelin.labactivitytracker.business.service;

public interface UpdateEntityService<DataTransferType, EntityIdType> {
    void update(EntityIdType id, DataTransferType dataTransferObject);
}
