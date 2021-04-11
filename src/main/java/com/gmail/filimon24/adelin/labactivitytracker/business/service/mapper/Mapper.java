package com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper;

public interface Mapper<DataAccessObject, DataTransferObject> {

    DataTransferObject daoToDto(DataAccessObject dataAccessObject);

    DataAccessObject dtoToDao(DataTransferObject dataTransferObject);
}
