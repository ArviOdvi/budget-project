package com.budget.my.print;

import com.budget.my.BudgetService;
import com.budget.my.records.CommonRecord;
import com.budget.my.records.ExpenseRecord;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PrintExpenses {
    private final BudgetService budgetService;

    public PrintExpenses(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    public BigDecimal printExpenses() {
        BigDecimal expenseAmount = BigDecimal.ZERO;
        Map<Integer, List<CommonRecord>> records = budgetService.getCommonRecords();
        for (List<CommonRecord> commonRecordList : records.values()) {
            for (CommonRecord commonRecord : commonRecordList) {
                if (commonRecord instanceof ExpenseRecord) {
                    ExpenseRecord expenseRecord = (ExpenseRecord) commonRecord;
                    expenseAmount = expenseAmount.add(expenseRecord.getAmount());
                }
            }
        }
        return expenseAmount;
    }
}
