package com.budget.my.fileoperations;

import com.budget.my.records.CommonRecord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class RecordsFileWriter {

    private final LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter)
            .registerTypeAdapter(CommonRecord.class, new FileRecordAdapter(new GsonBuilder().create()))
            .setPrettyPrinting()
            .create();

    public void saveRecords(String fileName, Map<Integer, List<CommonRecord>> commonrecords) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Type mapType = new TypeToken<Map<Integer, List<CommonRecord>>>() {}.getType();
            gson.toJson(commonrecords, mapType, writer); // Nurodomas mapType
        } catch (IOException e) {
            System.err.println("Klaida rašant į failą " + fileName + ": " + e.getMessage()); // geresnis klaidos pranešimas
            // arba log.error("Klaida rašant į failą " + fileName, e); // Jeigu naudojate logavimo biblioteką
        }
    }
}