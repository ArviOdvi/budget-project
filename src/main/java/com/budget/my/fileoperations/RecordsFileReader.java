package com.budget.my.fileoperations;

import com.budget.my.BudgetService;
import com.budget.my.records.CommonRecord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

public class RecordsFileReader {
    private final BudgetService budgetService;
    private final RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
    private final LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter)
            .registerTypeAdapter(CommonRecord.class, new FileRecordAdapter(new GsonBuilder().create()))
            .setPrettyPrinting()
            .create();

    public RecordsFileReader(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    public Map<Integer, List<CommonRecord>> loadRecords(String fileName, Map<Integer, List<CommonRecord>> commonrecords) {
        File file = new File(fileName);
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(fileName)) {
                Type mapType = new TypeToken<HashMap<Integer, List<CommonRecord>>>() {}.getType();
                Map<Integer, List<CommonRecord>> loadedRecords = gson.fromJson(fileReader, mapType);
                if (loadedRecords != null) {
                    if (loadedRecords.isEmpty()) {
                        budgetService.setCounter(0);
                        return loadedRecords;
                    } else {
                        budgetService.setCounter(Collections.max(loadedRecords.keySet()));
                        return loadedRecords;
                    }
                }
            } catch (IOException e) {
                System.err.println("Klaida skaitant failą " + fileName + ": " + e.getMessage());
            }
        } else {
            System.err.println("Failas " + fileName + " nerastas. Sukuriamas naujas failas.");
            try {
                if (file.createNewFile()) {
                    System.err.println("Failas " + fileName + " sėkmingai sukurtas.");
                    recordsFileWriter.saveRecords(fileName, commonrecords);
                } else {
                    System.err.println("Nepavyko sukurti failo " + fileName);
                }
            } catch (IOException e) {
                System.err.println("Klaida kuriant failą " + fileName);
            }
        }
        return commonrecords;
    }
}
