package com.budget.my;

import java.util.ArrayList;
import java.util.List;

public class BudgetService {
    private final List<IncomeRecord> incomeRecords = new ArrayList<>();
    private final List<ExpenseRecord> expenseRecords = new ArrayList<>();

    public List<IncomeRecord> getIncomeRecords() {
        return incomeRecords;
    }

    public void setIncomeRecord(final IncomeRecord incomeRecords) {
        this.incomeRecords.add(incomeRecords);
    }

    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

    public void setExpenseRecords(final ExpenseRecord expenseRecords) {
        this.expenseRecords.add(expenseRecords);
    }
}
