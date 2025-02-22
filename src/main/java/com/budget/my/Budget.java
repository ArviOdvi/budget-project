package com.budget.my;

import java.math.BigDecimal;
import java.util.List;

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
        List<IncomeRecord> incomes = budgetService.getIncomeRecords();
        for (IncomeRecord income : incomes) {
            incomeAmount = incomeAmount.add(income.getAmount());
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
