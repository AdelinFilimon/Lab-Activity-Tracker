package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

public interface ModelMapper<EntityType, DataAccessType> {
    DataAccessType entityToDataAccess(EntityType entityTypeObject);
    EntityType dataAccessToEntity(DataAccessType dataAccessType);
}
