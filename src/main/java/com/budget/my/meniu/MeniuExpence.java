package com.budget.my.meniu;

import com.budget.my.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class MeniuExpence {
    private final Scanner scanner;
    private final BudgetService budgetService;

    public MeniuExpence(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
    }
    public void typeExpenceRecord() {
        int choice;
        while (true) {
            System.out.println ("+++++++++++++++++++++++++++++++\n" +
                    "+           IŠLAIDOS           +\n" +
                    "+++++++++++++++++++++++++++++++\n" +
                    "+     1. Iš sąskaitos           +\n" +
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
                    transferExpenceRecord();
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
    public void transferExpenceRecord() {
        UUID uuid = UUID.randomUUID();
        System.out.println("Prašome įvesti išlaidas:");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Ar tai darbo užmokestis(T/N):");
        String expenceType = scanner.nextLine();
        String category = "Other";
        if ("T".equalsIgnoreCase(expenceType)) {
            category = "Salary";
        }
        int counter = budgetService.getCounter()+1;
        budgetService.setCounter(counter);
        final ExpenseRecord expenseRecord = new ExpenseRecord(uuid.hashCode(),  amount,
                LocalDateTime.now(), "",category, "cash");
        Map<Integer, List<CommonRecord>> expenseRecords = budgetService.getCommonRecords();
        expenseRecords.putIfAbsent(counter, new ArrayList<>());
        expenseRecords.get(counter).add(expenseRecord);
    }
}
