package com.budget.my.meniu;

import com.budget.my.Budget;
import com.budget.my.BudgetService;
import com.budget.my.fileoperations.RecordsFileWriter;
import com.budget.my.print.PrintExpensesAmount;
import com.budget.my.print.PrintIncomesAmount;
import com.budget.my.print.PrintRecords;
import com.budget.my.records.CommonRecord;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Meniu {
    private final Scanner scanner;
    private final BudgetService budgetService;
    private final Budget budget;
    private final MeniuIncome meniuIncome;
    private final MeniuExpense meniuExpense;
    private final PrintIncomesAmount printIncomesAmount;
    private final PrintExpensesAmount printExpensesAmount;
    private final PrintRecords printRecords;
    private final MeniuChange meniuChange;
    private final RecordsFileWriter recordsFileWriter;

    public Meniu(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
        this.printIncomesAmount = new PrintIncomesAmount(budgetService);
        this.printExpensesAmount = new PrintExpensesAmount(budgetService);
        this.printRecords = new PrintRecords(budgetService);
        this.budget = new Budget(budgetService);
        this.meniuIncome = new MeniuIncome(scanner, budgetService);
        this.meniuExpense = new MeniuExpense(scanner, budgetService);
        this.meniuChange = new MeniuChange(budgetService, scanner);
        this.recordsFileWriter = new RecordsFileWriter();
    }
    public void meniu() {

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
         try {
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
                    System.out.println("\033[32mVisos pajamos: " + printIncomesAmount.printIncomes() + "\033[0m\n");
                    break;
                }
                case 4: {
                    System.out.println("\033[31mVisos išlaidos: " + printExpensesAmount.printExpenses() + "\033[0m\n");
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
                    Map<Integer, List<CommonRecord>> newRecords = budgetService.reindexRecords();
                    recordsFileWriter.saveRecords("records.json", newRecords);
                    return;
                }
                default: { System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 8 ");
                }
            }
         } catch (java.util.InputMismatchException e) {
             System.out.println("Klaida! Prašome įvesti tik skaičių.");
             scanner.next();
         }
        }
    }
}
