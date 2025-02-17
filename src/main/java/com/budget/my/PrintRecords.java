package com.budget.my;

import java.util.List;

public class PrintRecords {
    private final BudgetService budgetService;

    public PrintRecords(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    private void printIncomeRecords() {
        final List<IncomeRecord> records = budgetService.getIncomeRecords();
        for (IncomeRecord record : records) {
            System.out.println(record);
        }
    }
    private void printExpenseRecords() {
        final List<ExpenseRecord> records = budgetService.getExpenseRecords();
        for (ExpenseRecord record : records) {
            System.out.println(record);
        }
    }
}
