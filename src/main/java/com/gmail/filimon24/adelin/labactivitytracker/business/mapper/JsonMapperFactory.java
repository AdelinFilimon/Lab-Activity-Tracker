package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

public interface JsonMapperFactory {
    JsonMapper<?> obtainMapper(String mapperType);
}
