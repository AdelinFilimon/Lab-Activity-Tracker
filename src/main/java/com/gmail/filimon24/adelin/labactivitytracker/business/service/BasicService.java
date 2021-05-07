package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import java.util.List;

public interface BasicService<DataTransferType, EntityIdType> extends CreateEntityService {
    DataTransferType get(EntityIdType id);
    List<DataTransferType> getAll();
    void delete(EntityIdType id);
    void deleteAll();
}
