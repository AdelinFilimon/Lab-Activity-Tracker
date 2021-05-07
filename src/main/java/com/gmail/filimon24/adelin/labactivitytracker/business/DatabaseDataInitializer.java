package com.gmail.filimon24.adelin.labactivitytracker.business;

import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.JsonMapperFactory;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.CreateEntityService;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.CreateEntityServiceFactory;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class DatabaseDataInitializer {

    private final ResourceLoader resourceLoader;
    private final JsonMapperFactory jsonMapperFactory;
    private final CreateEntityServiceFactory createEntityServiceFactory;

    private void insertRows(String key, JSONArray data) {
        CreateEntityService service = createEntityServiceFactory.obtainService(key);

        for (int i = 0; i < data.length(); i++) {
            JSONObject rowJson = data.getJSONObject(i);
            Object rowObject = jsonMapperFactory.obtainMapper(key).fromJson(rowJson);
            try {
                service.create(rowObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        JSONObject initialDataObject;

        try {
            File initialDataFile = resourceLoader.getResource("classpath:initialData.json").getFile();
            String initialDataString = new String(Files.readAllBytes(initialDataFile.toPath()));
            initialDataObject = new JSONObject(initialDataString);

            Iterator<String> keys = initialDataObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                insertRows(key, initialDataObject.getJSONArray(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


