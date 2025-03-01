package com.budget.my.print;

import com.budget.my.BudgetService;
import com.budget.my.CommonRecord;
import com.budget.my.IncomeRecord;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DisplayIncome {
    private final BudgetService budgetService;
    private BigDecimal incomeAmount;
    public DisplayIncome(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    public BigDecimal displayIncome() {
        incomeAmount = BigDecimal.ZERO;
        Map<Integer, List<CommonRecord>> records = budgetService.getCommonRecords();
        for (List<CommonRecord> commonRecordList : records.values()) {
            for (CommonRecord commonRecord : commonRecordList) {
                if (commonRecord instanceof IncomeRecord) {
                    IncomeRecord incomeRecord = (IncomeRecord) commonRecord;
                    incomeAmount = incomeAmount.add(incomeRecord.getAmount());
                }
            }
        }
        return incomeAmount;
    }
}
