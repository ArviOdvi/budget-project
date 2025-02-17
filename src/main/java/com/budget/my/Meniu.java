package com.budget.my;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Meniu {

    static final BudgetService budgetService = new BudgetService();
    public static int meniu() {

        final IncomeRecord incomeRecord = new IncomeRecord(
                BigDecimal.valueOf(1500), "Salary", LocalDateTime.now(), true, null);
        final IncomeRecord incomeRecordCash = new IncomeRecord(
                BigDecimal.valueOf(500), "Sale bike", LocalDateTime.now(), false, null);
        final ExpenseRecord expenseRecord = new ExpenseRecord(
                BigDecimal.valueOf(100), "Food", LocalDateTime.now(), PaymentMethodType.CARD, new BankCard("Revolut", "1234"));
        final ExpenseRecord expenseRecordCash = new ExpenseRecord(
                BigDecimal.valueOf(800), "New bike", LocalDateTime.now(), PaymentMethodType.CASH);

        final Budget budget = new Budget(budgetService);

        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("-------------------------------\n" +
                    "-           MENIU              -\n" +
                    "-------------------------------\n" +
                    "-     1. Įvesti pajamas       -\n" +
                    "-     2. Įvesti išlaidas      -\n" +
                    "-     3. Visos pajamos        -\n" +
                    "-     4. Visos išlaidos       -\n" +
                    "-     5. Balansas             -\n" +
                    "-     6. Programos pabaiga    -\n" +
                    "-------------------------------\n");
            if (!sc.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                sc.next(); // Išvalome neteisingą įvestį
                continue;  // Pereiname prie naujo ciklo
            }
            choice = sc.nextInt();
            switch (choice) {
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
                case 6: {
                    return 0;
                }
                default: { System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 6.");
                }
            }
        }
    }
}
