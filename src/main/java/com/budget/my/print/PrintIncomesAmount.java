package com.budget.my.print;

import com.budget.my.BudgetService;
import com.budget.my.records.CommonRecord;
import com.budget.my.records.IncomeRecord;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
public class PrintIncomesAmount {
    private final BudgetService budgetService;
    public PrintIncomesAmount(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    public BigDecimal printIncomes() {
        BigDecimal incomeAmount = BigDecimal.ZERO;
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
