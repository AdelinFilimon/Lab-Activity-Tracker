package com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface Mapper<DataAccessObject, DataTransferObject> {

    default DataTransferObject daoToDto(DataAccessObject dataAccessObject) {
        throw new NotImplementedException();
    }

    default DataAccessObject dtoToDao(DataTransferObject dataTransferObject) {
        throw new NotImplementedException();
    }
}
