package com.budget.my.meniu;

import com.budget.my.*;
import com.budget.my.enum_data.ExpenseCategory;
import com.budget.my.enum_data.ExpenseType;
import com.budget.my.records.CommonRecord;
import com.budget.my.records.ExpenseRecord;
import com.budget.my.utils.UniqueIdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class MeniuExpense {
    private final Scanner scanner;
    private final BudgetService budgetService;

    public MeniuExpense(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
    }
    public void fillExpenceRecordFields() {

        String id = UniqueIdGenerator.generateUniqueId();
        System.out.print("\033[31mPrašome įvesti išlaidas EUR: \033[0m");
        BigDecimal amount = scanner.nextBigDecimal();
        ExpenseType expenseType = fillExpenceRecordTypeField();
        ExpenseCategory expenseCategory = fillExpenceRecordCategoryField();
        scanner.nextLine();
        System.out.print("\033[31mJūsų komentaras(iki 20 simbolių): \033[0m");
        String comment = scanner.nextLine();
        comment = comment.substring(0, Math.min(comment.length(), 20));
        int counter = budgetService.getCounter()+1;
        budgetService.setCounter(counter);
        final ExpenseRecord expenseRecord = new ExpenseRecord(id,  amount,
                LocalDateTime.now(), comment,expenseCategory, expenseType);
        Map<Integer, List<CommonRecord>> expenseRecords = budgetService.getCommonRecords();
        expenseRecords.putIfAbsent(counter, new ArrayList<>());
        expenseRecords.get(counter).add(expenseRecord);
    }
    public ExpenseType fillExpenceRecordTypeField() {
        int choice;
        while (true) {
            System.out.println (
                    "\033[31m-------------------------------\n" +
                            "-           IŠLAIDOS          -\n" +
                            "-------------------------------\n" +
                            "-     1  Iš sąskaitos         -\n" +
                            "-     2  Kortele              -\n" +
                            "-     3  Grynais              -\n" +
                            "-------------------------------\n\033[0m");
        try {
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    return ExpenseType.BANK_TRANSFER;
                }
                case 2: {
                    return ExpenseType.CARD;
                }
                case 3: {
                    return ExpenseType.CASH;
                }
                default: {
                    System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 4.");
                }
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Klaida! Prašome įvesti tik skaičių.");
            scanner.next();
        }
        }
    }
    public ExpenseCategory fillExpenceRecordCategoryField(){
        int choice;
        while (true) {
            System.out.println(
                    "\033[31m-------------------------------\n" +
                            "-    IŠLAIDŲ KATEGORIJA       -\n" +
                            "-------------------------------\n" +
                            "-       1  Mokesčiams         -\n" +
                            "-       2  Nuomai             -\n" +
                            "-       3  Lizingui           -\n" +
                            "-       4  Maistui            -\n" +
                            "-       5  Daiktams           -\n" +
                            "-       6  Kitam              -\n" +
                            "-------------------------------\n\033[0m");
        try {
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                   return ExpenseCategory.TAXES;
                }
                case 2: {
                    return ExpenseCategory.RENTAL_FEE;
                }
                case 3: {
                    return ExpenseCategory.LEASING_FEE;
                }
                case 4: {
                    return ExpenseCategory.FOOD_EXPENSES;
                }
                case 5: {
                    return ExpenseCategory.BUY_ITEMS;
                }
                case 6: {
                    return ExpenseCategory.OTHERS;
                }
                default: {
                    System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 4.");
                }
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Klaida! Prašome įvesti tik skaičių.");
            scanner.next();
        }
        }
    }

}
