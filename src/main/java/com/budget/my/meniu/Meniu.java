package com.budget.my.meniu;

import com.budget.my.Budget;
import com.budget.my.BudgetService;

import java.util.Scanner;
public class Meniu {
    private final Scanner scanner;
    private final BudgetService budgetService; // Naudojame tą patį BudgetService egzempliorių
    private final Budget budget;
    private final MeniuIncome meniuIncome; // Perduodame BudgetService
    private final MeniuExpence meniuExpence;
    public Meniu(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
        this.budget = new Budget(budgetService);
        this.meniuIncome = new MeniuIncome(scanner, budgetService);
        this.meniuExpence = new MeniuExpence(scanner, budgetService);
    }
    public void meniu() {

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
                    meniuIncome.typeIncomeRecord();
                    break;
                }
                case 2: {
                    meniuExpence.typeExpenceRecord();
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
                    budgetService.saveIncomeRecords("income_records.json");
                    budgetService.saveExpenceRecords("expense_records.json");
                    return;
                }
                default: { System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 6.");
                }
            }
        }
    }
}
