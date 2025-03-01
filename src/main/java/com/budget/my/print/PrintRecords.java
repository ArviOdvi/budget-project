package com.budget.my.print;

import com.budget.my.BudgetService;
import com.budget.my.CommonRecord;

import java.util.List;
import java.util.Map;

public class PrintRecords {
    private final BudgetService budgetService;

    public PrintRecords(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    private void printIncomeRecords() {
        Map<Integer, List<CommonRecord>> incomesMap = budgetService.getCommonRecords();
        for (List<CommonRecord> incomeRecordsList : incomesMap.values()) {  // Iterate through the *values* (lists)
            for (CommonRecord incomeCommonRecord : incomeRecordsList) { // Iterate through each IncomeRecord in the list
                System.out.println(incomeCommonRecord);
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
