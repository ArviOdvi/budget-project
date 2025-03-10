package com.budget.my.fileoperations;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime>{
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
