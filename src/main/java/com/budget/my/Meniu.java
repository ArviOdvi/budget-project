package com.budget.my;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Meniu {
    private final Scanner scanner;
    private final BudgetService budgetService;
    private final Budget budget;

    public Meniu(Scanner scanner) {
        this.budgetService = new BudgetService();
        this.scanner = scanner;
        this.budget = new Budget(budgetService);
    }

    public void meniu() {

        final IncomeRecord incomeRecord = new IncomeRecord(
                BigDecimal.valueOf(1500), "Salary", LocalDateTime.now(), true, null);
        final IncomeRecord incomeRecordCash = new IncomeRecord(
                BigDecimal.valueOf(500), "Sale bike", LocalDateTime.now(), false, null);
        final ExpenseRecord expenseRecord = new ExpenseRecord(
                BigDecimal.valueOf(100), "Food", LocalDateTime.now(), PaymentMethodType.CARD, new BankCard("Revolut", "1234"));
        final ExpenseRecord expenseRecordCash = new ExpenseRecord(
                BigDecimal.valueOf(800), "New bike", LocalDateTime.now(), PaymentMethodType.CASH);




        int choice;
        while (true) {
            System.out.println  ("*******************************\n" +
                                 "*           MENIU             *\n" +
                                 "*******************************\n" +
                                 "*     1. Įvesti pajamas       *\n" +
                                 "*     2. Įvesti išlaidas      *\n" +
                                 "*     3. Visos pajamos        *\n" +
                                 "*     4. Visos išlaidos       *\n" +
                                 "*     5. Balansas             *\n" +
                                 "*     6. Programos pabaiga    *\n" +
                                 "*******************************\n");
            if (!scanner.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                scanner.next(); // Išvalome neteisingą įvestį
                continue;  // Pereiname prie naujo ciklo
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    typeIncomeRecord();
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
                    return;
                }
                default: { System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 6.");
                }
            }
        }
    }
    public void typeIncomeRecord() {
        int choice;
        while (true) {
            System.out.println ("+++++++++++++++++++++++++++++++\n" +
                                "+           PAJAMOS           +\n" +
                                "+++++++++++++++++++++++++++++++\n" +
                                "+     1. Į sąskaitą           +\n" +
                                "+     2. Grynais              +\n" +
                                "+     3. Grįžti į meniu       +\n" +
                                "+++++++++++++++++++++++++++++++\n");
            if (!scanner.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                scanner.next(); // Išvalome neteisingą įvestį
                continue;  // Pereiname prie naujo ciklo
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    transferIncomeRecord();
                    break;
                }
                case 2: {
                    // budgetService.setExpenseRecords(expenseRecord);
                    // budgetService.setExpenseRecords(expenseRecordCash);
                    break;
                }
                case 3: {
                    return;
                }
                default: {
                    System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 3.");
                }
            }
        }
    }
    public void transferIncomeRecord() {
        System.out.println("Prašome įvesti pajamas:");
        BigDecimal amount = scanner.nextBigDecimal();
        System.out.println("Ar tai darbo užmokestis(T/N):");
        String incomeType = scanner.nextLine();
        String category = "Other";
        if ("T".equalsIgnoreCase(incomeType)) {
            category = "Salary";
        }
            final IncomeRecord incomeRecord = new IncomeRecord(
                amount, category, LocalDateTime.now(), true, null);
            budgetService.setIncomeRecord(incomeRecord);
    }
}
