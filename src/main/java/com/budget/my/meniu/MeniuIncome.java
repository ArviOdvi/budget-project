package com.budget.my.meniu;

import com.budget.my.CommonRecord;
import com.budget.my.BudgetService;
import com.budget.my.IncomeRecord;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class MeniuIncome {
    private final Scanner scanner;
    private final BudgetService budgetService;
    public MeniuIncome(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
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
            choice =   scanner.nextInt();
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
        UUID uuid = UUID.randomUUID();
        System.out.println("Prašome įvesti pajamas:");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Ar tai darbo užmokestis(T/N):");
        String incomeType = scanner.nextLine();
        String category = "Other";
        if ("T".equalsIgnoreCase(incomeType)) {
            category = "Salary";
        }
        int counter = budgetService.getCounter()+1;
        budgetService.setCounter(counter);
        final CommonRecord incomeCommonRecord = new IncomeRecord(uuid.hashCode(), amount,
                LocalDateTime.now(), "",category, "cash");
        Map<Integer, List<CommonRecord>> incomeRecords = budgetService.getCommonRecords();
        incomeRecords.putIfAbsent(counter, new ArrayList<>());
        incomeRecords.get(counter).add(incomeCommonRecord);
    }
}
