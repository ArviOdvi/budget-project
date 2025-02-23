package com.budget.my;

import java.util.List;
import java.util.Map;

public class PrintRecords {
    private final BudgetService budgetService;

    public PrintRecords(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    private void printIncomeRecords() {
        Map<Integer, List<IncomeRecord>> incomesMap = budgetService.getIncomeRecords();
        for (List<IncomeRecord> incomeRecordsList : incomesMap.values()) {  // Iterate through the *values* (lists)
            for (IncomeRecord incomeRecord : incomeRecordsList) { // Iterate through each IncomeRecord in the list
                System.out.println(incomeRecord);
            }
        }
    }
//    private void printExpenseRecords() {
//        final List<ExpenseRecord> records = budgetService.getExpenseRecords();
//        for (ExpenseRecord record : records) {
//            System.out.println(record);
//        }
//    }
}
