package com.budget.my;

import java.util.ArrayList;
import java.util.List;

public class BudgetService {

    private RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
    private RecordsFileReader recordsFileReader = new RecordsFileReader();
    private List<IncomeRecord> incomeRecords = new ArrayList<>();
    private List<ExpenseRecord> expenseRecords = new ArrayList<>();

    public BudgetService() {
        incomeRecords = recordsFileReader.loadIncomeRecords("income_records.json",incomeRecords);
        expenseRecords = recordsFileReader.loadExpenseRecords("expense_records.json",expenseRecords);
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