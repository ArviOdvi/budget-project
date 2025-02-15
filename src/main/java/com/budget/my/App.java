package com.budget.my;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    static final BudgetService budgetService = new BudgetService();

    public static void main(String[] args) {

        final IncomeRecord incomeRecord = new IncomeRecord(
                BigDecimal.valueOf(1500), "Salary", LocalDateTime.now(), true, null);
        final IncomeRecord incomeRecordCash = new IncomeRecord(
                BigDecimal.valueOf(500), "Sale bike", LocalDateTime.now(), false, null);
        final ExpenseRecord expenseRecord = new ExpenseRecord(
                BigDecimal.valueOf(100), "Food", LocalDateTime.now(), PaymentMethodType.CARD, new BankCard("Revolut", "1234"));
        final ExpenseRecord expenseRecordCash = new ExpenseRecord(
                BigDecimal.valueOf(800), "New bike", LocalDateTime.now(), PaymentMethodType.CASH);
        final Budget budget = new Budget(budgetService);
        while (true) {
            switch (Meniu.meniu()) {
                case 1: {
                    budgetService.setIncomeRecord(incomeRecord);
                    budgetService.setIncomeRecord(incomeRecordCash);
                    break;
                }
                case 2: {
                    budgetService.setExpenseRecords(expenseRecord);
                    budgetService.setExpenseRecords(expenseRecordCash);
                    break;
                }
                case 3: {
                    System.out.println(budget.displayIncome());
                    break;
                }
                case 4: {
                    System.out.println(budget.displayExpenses());
                    break;
                }
                case 5: {
                    budget.balance();
                    break;
                }
                case 6:
                    return;
            }
        }
    }

    private static void printIncomeRecords() {
        final List<IncomeRecord> records = budgetService.getIncomeRecords();
        for (IncomeRecord record : records) {
            System.out.println(record);
        }
    }

    private static void printExpenseRecords() {
        final List<ExpenseRecord> records = budgetService.getExpenseRecords();
        for (ExpenseRecord record : records) {
            System.out.println(record);
        }
    }
}