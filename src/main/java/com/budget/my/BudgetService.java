package com.budget.my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetService {
    public Integer counter;
    private RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
    private RecordsFileReader recordsFileReader = new RecordsFileReader(counter, this);
    private Map<Integer, List<IncomeRecord>> incomeRecords = new HashMap<>();
    private List<ExpenseRecord> expenseRecords = new ArrayList<>();

    public void setIncomeRecords(Map<Integer, List<IncomeRecord>> incomeRecords) {
        this.incomeRecords = incomeRecords;
    }

    public int getCounter() {
        return counter;
    }

    public Map<Integer, List<IncomeRecord>> getIncomeRecords() {
        return incomeRecords;
    }
    public BudgetService() {
        incomeRecords = recordsFileReader.loadIncomeRecords("income_records.json",incomeRecords);
        expenseRecords = recordsFileReader.loadExpenseRecords("expense_records.json",expenseRecords);
    }

    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    public void setExpenseRecord(final ExpenseRecord expenseRecord) { // Pakeistas pavadinimas į vienaskaitą
        this.expenseRecords.add(expenseRecord);
    }

    public void saveIncomeRecords(String filename) {
        recordsFileWriter.saveIncomeRecords(filename, incomeRecords);
    }
    public void saveExpenceRecords(String filename) {
        recordsFileWriter.saveExpenceRecords(filename, expenseRecords);
    }
}