package com.budget.my.meniu;

import com.budget.my.Budget;
import com.budget.my.BudgetService;
import com.budget.my.print.PrintExpenses;
import com.budget.my.print.PrintIncomes;
import com.budget.my.print.PrintRecords;

import java.util.Scanner;
public class Meniu {
    private final Scanner scanner;
    private final BudgetService budgetService; // Naudojame tą patį BudgetService egzempliorių
    private final Budget budget;
    private final MeniuIncome meniuIncome; // Perduodame BudgetService
    private final MeniuExpence meniuExpence;
    private final PrintIncomes printIncomes;
    private final PrintExpenses printExpenses;
    private final PrintRecords printRecords;
    private final MeniuChange meniuChange;

    public Meniu(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
        this.printIncomes = new PrintIncomes(budgetService);
        this.printExpenses = new PrintExpenses(budgetService);
        this.budget = new Budget(budgetService);
        this.meniuIncome = new MeniuIncome(scanner, budgetService);
        this.meniuExpence = new MeniuExpence(scanner, budgetService);
        this.meniuChange = new MeniuChange(budgetService, scanner);
        this.printRecords = new PrintRecords(budgetService);
    }
    public void meniu() throws InterruptedException {

        int choice;
        while (true) {
            System.out.println  ("*************************************\n" +
                                 "*           MENIU                   *\n" +
                                 "*************************************\n" +
                                 "*     1. Įvesti pajamas             *\n" +
                                 "*     2. Įvesti išlaidas            *\n" +
                                 "*     3. Visos pajamos              *\n" +
                                 "*     4. Visos išlaidos             *\n" +
                                 "*     5. Balansas                   *\n" +
                                 "*     6. Parodyti visus įrašus      *\n" +
                                 "*     7. Programos pabaiga          *\n" +
                                 "*************************************");
            if (!scanner.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                scanner.next(); // Išvalome neteisingą įvestį
                continue;  // Pereiname prie naujo ciklo
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    meniuIncome.enterIncomeRecord();
                    break;
                }
                case 2: {
                    meniuExpence.enterExpenceRecord();
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
                    meniuChange.meniuChange();
                    break;
                }
                case 7: {
                    budgetService.saveRecords("records.json");
                    return;
                }
                default: { System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 6.");
                }
            }
        }
    }
}
