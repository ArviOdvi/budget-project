package com.budget.my.meniu;

import com.budget.my.Budget;
import com.budget.my.BudgetService;
import com.budget.my.print.PrintExpenses;
import com.budget.my.print.PrintIncomes;
import com.budget.my.print.PrintRecords;
import com.budget.my.records.CommonRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Meniu {
    private final Scanner scanner;
    private final BudgetService budgetService; // Naudojame tą patį BudgetService egzempliorių
    private final Budget budget;
    private final MeniuIncome meniuIncome; // Perduodame BudgetService
    private final MeniuExpense meniuExpense;
    private final PrintIncomes printIncomes;
    private final PrintExpenses printExpenses;
    private final PrintRecords printRecords;
    private final MeniuChange meniuChange;

    public Meniu(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
        this.printIncomes = new PrintIncomes(budgetService);
        this.printExpenses = new PrintExpenses(budgetService);
        this.printRecords = new PrintRecords(budgetService);
        this.budget = new Budget(budgetService);
        this.meniuIncome = new MeniuIncome(scanner, budgetService);
        this.meniuExpense = new MeniuExpense(scanner, budgetService);
        this.meniuChange = new MeniuChange(budgetService, scanner);
    }
    public void meniu() throws InterruptedException {

        int choice;
        while (true) {
            System.out.println  ("*************************************\n" +
                                 "*           MENIU                   *\n" +
                                 "*************************************\n" +
                                 "*       1 Įvesti pajamas            *\n" +
                                 "*       2 Įvesti išlaidas           *\n" +
                                 "*       3 Visos pajamos             *\n" +
                                 "*       4 Visos išlaidos            *\n" +
                                 "*       5 Balansas                  *\n" +
                                 "*       6 Parodyti visus įrašus     *\n" +
                                 "*       7 Koreguoti įrašus          *\n" +
                                 "*       8 Programos pabaiga         *\n" +
                                 "*************************************\n");
            if (!scanner.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                scanner.next(); // Išvalome neteisingą įvestį
                continue;  // Pereiname prie naujo ciklo
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    meniuIncome.fillIncomeRecord();
                    break;
                }
                case 2: {
                    meniuExpense.fillExpenceRecordFields();
                    break;
                }
                case 3: {
                    System.out.println("\033[32mVisos pajamos: " + printIncomes.printIncomes() + "\033[0m\n");
                    break;
                }
                case 4: {
                    System.out.println("\033[31mVisos išlaidos: " + printExpenses.printExpenses() + "\033[0m\n");
                    break;
                }
                case 5: {
                    budget.balance();
                    break;
                }
                case 6: {
                    scanner.nextLine();
                    printRecords.printRecords();
                    break;
                }
                case 7: {
                    scanner.nextLine();
                    meniuChange.meniuChange();
                    break;
                }
                case 8: {
                    Map<Integer, List<CommonRecord>> commonRecords = budgetService.getCommonRecords();
                    Map<Integer, List<CommonRecord>> newRecords = reindexRecords(commonRecords);
                    budgetService.setCommonRecords(newRecords);
                    budgetService.saveRecords("records.json");
                    return;
                }
                default: { System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 8 ");
                }
            }
        }
    }
    public Map<Integer, List<CommonRecord>> reindexRecords(Map<Integer, List<CommonRecord>> commonRecords){
        Map<Integer, List<CommonRecord>> newRecords = new HashMap<>();
        int newCounter = 1;
        for (List<CommonRecord> records : commonRecords.values()) {
            newRecords.put(newCounter, records);
            newCounter++;
        }
        return newRecords;
    }
}
