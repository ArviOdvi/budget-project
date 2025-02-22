package com.budget.my;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class RecordsFileWriter {
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
            .setPrettyPrinting()
            .create();
       public void saveIncomeRecords(String fileName, List<IncomeRecord> incomeRecords) {
       try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(incomeRecords, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void saveExpenceRecords(String fileName, List<ExpenseRecord> expenseRecords) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(expenseRecords, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}