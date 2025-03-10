package com.budget.my.meniu;

import com.budget.my.BudgetService;
import com.budget.my.enum_data.ExpenseCategory;
import com.budget.my.enum_data.ExpenseType;
import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.enum_data.IncomeType;
import com.budget.my.print.PrintRecords;
import com.budget.my.records.CommonRecord;
import com.budget.my.records.ExpenseRecord;
import com.budget.my.records.IncomeRecord;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MeniuChange {
    private final BudgetService budgetService;
    private final Scanner scanner;
    private final MeniuIncome meniuIncome;
    private final MeniuExpense meniuExpense;
    private final PrintRecords printRecords;

    public MeniuChange(BudgetService budgetService, Scanner scanner) {
        this.budgetService = budgetService;
        this.scanner = scanner;
        this.printRecords = new PrintRecords(budgetService);
        this.meniuIncome = new MeniuIncome(scanner,budgetService);
        this.meniuExpense = new MeniuExpense(scanner, budgetService);
    }

    public void meniuChange(){
        boolean continueEditing = true;
        while(continueEditing) {
            printRecords.printRecords();
            System.out.print("Įrašą norite koreguoti T/N?:  ");
            String recordCorrect = scanner.nextLine();
            if ("T".equalsIgnoreCase(recordCorrect)) {
                changeRecord();
            } else {
                System.out.print("Įrašą norite ištrinti T/N?:  ");
                String recordDelete = scanner.nextLine();
                if ("T".equalsIgnoreCase(recordDelete)) {
                    deleteRecord();
                } else {
                    System.out.print("Grįžti T/N?:  ");
                    String returnEntry = scanner.nextLine();
                    if ("T".equalsIgnoreCase(returnEntry)){
                        continueEditing = false;
                    }
                }
            }
        }
    }

    public void changeRecord(){
        System.out.println("Kurį įrašą redaguosit? ");
        int selectedEntry = scanner.nextInt();
        scanner.nextLine();
        Map<Integer, List<CommonRecord>> records = budgetService.getCommonRecords();
        if (records.containsKey(selectedEntry)) {
            List<CommonRecord> recordList = records.get(selectedEntry);
            if (recordList != null && !recordList.isEmpty()) {
                CommonRecord record = recordList.getFirst();
                if (record instanceof IncomeRecord) {
                    incomeRecordChange((IncomeRecord) record);
                } else if (record instanceof ExpenseRecord) {
                    expenseRecordChange((ExpenseRecord) record);
                }
            } else {
                System.out.println("Įrašų sąrašas tuščias.");
            }
        } else {
            System.out.println("Įrašas " + selectedEntry + " nerastas.");
        }
    }

    public void deleteRecord(){
        System.out.println("Kurį įrašą norite pašalinti? ");
        int selectedEntry = scanner.nextInt();
        scanner.nextLine();
        Map<Integer, List<CommonRecord>> records = budgetService.getCommonRecords();
        if (records.containsKey(selectedEntry)) {
              records.remove(selectedEntry);
        } else {
            System.out.println("Įrašas " + selectedEntry + " nerastas.");
        }
    }

    public void incomeRecordChange(IncomeRecord incomeRecord) {
        BigDecimal incomeAmount = BigDecimal.ZERO;
        System.out.println("Ar norite pakeisti pajamų sumą T/N? ");
        String amountCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(amountCorrect)) {
            System.out.println("Įveskite naują pajamų sumą: ");
            incomeAmount = scanner.nextBigDecimal();
            incomeRecord.setAmount(incomeAmount);
            scanner.nextLine();
        }
        System.out.println("Ar norite pakeisti pajamų tipą T/N? ");
        String typeCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(typeCorrect)) {
            IncomeType incomeType = meniuIncome.fillIncomeRecordTypeField();
            incomeRecord.setIncomeType(incomeType);
            scanner.nextLine();
        }
        System.out.println("Ar norite pakeisti pajamų kategoriją T/N? ");
        String categoryCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(categoryCorrect)) {
            IncomeCategory incomeCategory = meniuIncome.fillIncomeRecordCategoryField();
            incomeRecord.setIncomeCategory(incomeCategory);
            scanner.nextLine();
        }
        System.out.println("Ar norite pakeisti komentarą T/N? ");
        String commentCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(commentCorrect)) {
            System.out.println("Komentaras(iki 20 simbolių): ");
            String comment = scanner.nextLine();
            comment = comment.substring(0, Math.min(comment.length(), 20));
            incomeRecord.setOtherInfo(comment);
        }
    }

    public void expenseRecordChange(ExpenseRecord expenseRecord) {
        BigDecimal expenseAmount = BigDecimal.ZERO;
        System.out.println("Ar norite pakeisti išlaidų sumą T/N? ");
        scanner.nextLine();
        String recordCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(recordCorrect)) {
            System.out.println("Įveskite naują išlaidų sumą: ");
            expenseAmount = scanner.nextBigDecimal();
            expenseRecord.setAmount(expenseAmount);
        }
        System.out.println("Ar norite pakeisti išlaidų tipą T/N? ");
        String typeCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(typeCorrect)) {
            ExpenseType expenseType = meniuExpense.fillExpenceRecordTypeField();
            expenseRecord.setExpenseType(expenseType);
        }
        System.out.println("Ar norite pakeisti išlaidų kategoriją T/N? ");
        String categoryCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(categoryCorrect)) {
            ExpenseCategory expenseCategory = meniuExpense.fillExpenceRecordCategoryField();
            expenseRecord.setExpenseCategory(expenseCategory);
        }
        System.out.println("Ar norite pakeisti komentarą T/N? ");
        String commentCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(commentCorrect)) {
            System.out.println("Komentaras(iki 20 simbolių): ");
            String comment = scanner.nextLine();
            comment = comment.substring(0, 20);
            expenseRecord.setOtherInfo(comment);
        }
    }
}
