package com.budget.my.fileoperations;

import com.budget.my.BudgetService;
import com.budget.my.CommonRecord;
import com.budget.my.ExpenseRecord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RecordsFileReader {
    BudgetService budgetService;
    int counter;
    RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
    private final TypeAdapter<LocalDateTime> localDateTimeAdapter = new TypeAdapter<LocalDateTime>() {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); // Jūsų datos/laiko formatas

        @Override  // @Override anotacija yra gera praktika
        public void write(JsonWriter out, LocalDateTime dateTime) throws IOException { // write() metodo įgyvendinimas
            if (dateTime == null) {
                out.nullValue();
            } else {
                out.value(formatter.format(dateTime));
            }
        }

        @Override // Taip pat turite įgyvendinti read() metodą
        public LocalDateTime read(JsonReader in) throws IOException {
            switch (in.peek()) {
                case NULL:
                    in.nextNull();
                    return null;
                default:
                    String dateTimeString = in.nextString();
                    return LocalDateTime.parse(dateTimeString, formatter);
            }
        }
    };
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter)
            .registerTypeAdapter(CommonRecord.class, new CommonRecordAdapter(new GsonBuilder().create()))
            .setPrettyPrinting()
            .create();

    public RecordsFileReader(int counter, BudgetService budgetService) {
        this.budgetService = budgetService;
        this.counter = counter;

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
                        System.out.println(loadedRecords);
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
                if (file.createNewFile()) { // Sukuriame failą
                    System.err.println("Failas " + fileName + " sėkmingai sukurtas.");
                    // Čia galite pridėti pradinius pajamų įrašus į naują failą, jei reikia
                    recordsFileWriter.saveRecords(fileName, commonrecords);
                    // Išsaugome tuščią sąrašą, kad failas būtų paruoštas
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
