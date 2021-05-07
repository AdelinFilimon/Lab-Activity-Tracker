package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import org.json.JSONObject;

public interface JsonMapper<Type> {
    Type fromJson(JSONObject jsonObject);
}
