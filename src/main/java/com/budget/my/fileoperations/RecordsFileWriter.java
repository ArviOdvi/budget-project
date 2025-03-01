package com.budget.my.fileoperations;

import com.budget.my.CommonRecord;
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
import java.util.Map;

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
       public void saveRecords(String fileName, Map<Integer, List<CommonRecord>> commonrecords) {
       try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(commonrecords, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}