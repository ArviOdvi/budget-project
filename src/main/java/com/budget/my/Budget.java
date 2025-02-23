package com.budget.my;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Budget {
    private final BudgetService budgetService;
    private BigDecimal incomeAmount;
    private BigDecimal expenseAmount;

    public Budget(BudgetService budgetService) {

        this.budgetService = budgetService;
    }

    public void balance(){
        displayIncome();
        displayExpenses();
        System.out.println("Balansas: " + incomeAmount.subtract(expenseAmount));
    }
    public BigDecimal displayIncome() {
        incomeAmount = BigDecimal.ZERO;
        Map<Integer, List<IncomeRecord>> incomes = budgetService.getIncomeRecords();
        for (List<IncomeRecord> incomeRecordList : incomes.values()) {
            for (IncomeRecord incomeRecord : incomeRecordList) { // Iterate through each IncomeRecord in the list
                incomeAmount = incomeAmount.add(incomeRecord.getAmount());
            }
        }
        return incomeAmount;
    }
    public BigDecimal displayExpenses() {
        expenseAmount = BigDecimal.ZERO;
        List<ExpenseRecord> expenses = budgetService.getExpenseRecords();
        for (ExpenseRecord expense : expenses) {
            expenseAmount = expenseAmount.add(expense.getAmount());
        }
        return expenseAmount;
    }
}
