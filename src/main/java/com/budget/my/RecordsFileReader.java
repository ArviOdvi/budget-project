package com.budget.my;

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
import java.util.ArrayList;
import java.util.List;

public class RecordsFileReader {

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
            .setPrettyPrinting()
            .create();
    public List<IncomeRecord> loadIncomeRecords(String fileName, List<IncomeRecord> incomeRecords) {
        File file = new File(fileName);
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(fileName)) {
                Type listType = new TypeToken<ArrayList<IncomeRecord>>() {}.getType();
                List<IncomeRecord> loadedRecords = gson.fromJson(fileReader, listType);
                if (loadedRecords != null) {
                    incomeRecords.addAll(loadedRecords);
                    return incomeRecords;
                }
            } catch (IOException e) {
                System.err.println("Klaida skaitant failą " + fileName + ": " + e.getMessage());;
            }
        } else {
            System.err.println("Failas " + fileName + " nerastas. Sukuriamas naujas failas.");
            try {
                if (file.createNewFile()) { // Sukuriame failą
                    System.err.println("Failas " + fileName + " sėkmingai sukurtas.");
                    // Čia galite pridėti pradinius pajamų įrašus į naują failą, jei reikia
                    recordsFileWriter.saveIncomeRecords(fileName, incomeRecords); // Išsaugome tuščią sąrašą, kad failas būtų paruoštas
                } else {
                    System.err.println("Nepavyko sukurti failo " + fileName);
                }
            } catch (IOException e) {
                System.err.println("Klaida kuriant failą " + fileName);
            }
        }
        return incomeRecords;
    }
    public List<ExpenseRecord> loadExpenseRecords(String fileName, List<ExpenseRecord> expenseRecords) { // Atskiras metodas failo skaitymui
        File file = new File(fileName);
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(fileName)) {
                Type listType = new TypeToken<ArrayList<ExpenseRecord>>() {}.getType();
                List<ExpenseRecord> loadedRecords = gson.fromJson(fileReader, listType);

                if (loadedRecords != null) {
                    expenseRecords.addAll(loadedRecords);
                    return expenseRecords;
                }
            } catch (IOException e) {
                System.err.println("Klaida skaitant failą " + fileName + ": " + e.getMessage());;
            }
        } else {
            System.err.println("Failas " + fileName + " nerastas. Sukuriamas naujas failas.");
            try {
                if (file.createNewFile()) { // Sukuriame failą
                    System.err.println("Failas " + fileName + " sėkmingai sukurtas.");
                    // Čia galite pridėti pradinius pajamų įrašus į naują failą, jei reikia
                    recordsFileWriter.saveExpenceRecords(fileName, expenseRecords); // Išsaugome tuščią sąrašą, kad failas būtų paruoštas
                } else {
                    System.err.println("Nepavyko sukurti failo " + fileName);
                }
            } catch (IOException e) {
                System.err.println("Klaida kuriant failą " + fileName);
            }
        }
        return expenseRecords;
    }
}
