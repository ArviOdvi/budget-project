package com.budget.my;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BudgetService {
    private final List<IncomeRecord> incomeRecords = new ArrayList<>();
    private final List<ExpenseRecord> expenseRecords = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Sukuriame Gson objektą vieną kartą

    public BudgetService() {
        loadIncomeRecords("income_records.json");
        loadExpenseRecords("expense_records.json");
    }

    private void loadIncomeRecords(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(fileName)) {
                Type listType = new TypeToken<ArrayList<IncomeRecord>>() {}.getType();
                List<IncomeRecord> loadedRecords = gson.fromJson(fileReader, listType);

                if (loadedRecords != null) {
                    incomeRecords.addAll(loadedRecords);
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
                    saveIncomeRecords(fileName); // Išsaugome tuščią sąrašą, kad failas būtų paruoštas
                } else {
                    System.err.println("Nepavyko sukurti failo " + fileName);
                }
            } catch (IOException e) {
                System.err.println("Klaida kuriant failą " + fileName);
            }
        }
    }
    private void loadExpenseRecords(String fileName) { // Atskiras metodas failo skaitymui
        File file = new File(fileName);
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(fileName)) {
                Type listType = new TypeToken<ArrayList<ExpenseRecord>>() {}.getType();
                List<ExpenseRecord> loadedRecords = gson.fromJson(fileReader, listType);

                if (loadedRecords != null) {
                    expenseRecords.addAll(loadedRecords);
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
                    saveExpenseRecords(fileName); // Išsaugome tuščią sąrašą, kad failas būtų paruoštas
                } else {
                    System.err.println("Nepavyko sukurti failo " + fileName);
                }
            } catch (IOException e) {
                System.err.println("Klaida kuriant failą " + fileName);
            }
        }
    }

    public List<IncomeRecord> getIncomeRecords() {
        return incomeRecords;
    }

    public void setIncomeRecord(final IncomeRecord incomeRecord) {
        this.incomeRecords.add(incomeRecord);
    }

    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

    public void setExpenseRecords(final ExpenseRecord expenseRecord) { // Pakeistas pavadinimas į vienaskaitą
        this.expenseRecords.add(expenseRecord);
    }

    public void saveIncomeRecords(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(incomeRecords, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveExpenseRecords(String fileName) { // Naujas metodas išlaidų įrašų įrašymui
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(expenseRecords, writer);
        } catch (IOException e) {
            e.printStackTrace(); // Geriau būtų log'inti klaidą, o ne spausdinti į konsolę
        }
    }
}