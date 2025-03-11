package com.budget.my.meniu;

import com.budget.my.BudgetService;
import com.budget.my.enum_data.ExpenseCategory;
import com.budget.my.enum_data.ExpenseType;
import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.enum_data.IncomeType;
import com.budget.my.print.PrintRecords;
import com.budget.my.records_setting.CommonRecord;
import com.budget.my.records_setting.ExpenseRecord;
import com.budget.my.records_setting.IncomeRecord;
import com.budget.my.utils.CheckYesNo;

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
    private final CheckYesNo checkYesNo;
    private final String colorOn = "\033[33m";
    private final String colorOff = "\033[0m";

    public MeniuChange(BudgetService budgetService, Scanner scanner) {
        this.budgetService = budgetService;
        this.scanner = scanner;
        this.printRecords = new PrintRecords(budgetService);
        this.meniuIncome = new MeniuIncome(scanner,budgetService);
        this.meniuExpense = new MeniuExpense(scanner, budgetService);
        this.checkYesNo = new CheckYesNo(scanner);
    }

    public void meniuChange(){
        boolean continueEditing = true;
        while(continueEditing) {
            printRecords.printRecords();
            if (checkYesNo.askAndConfirm(colorOn + "Įrašą norite koreguoti" + colorOff)) {
                changeRecord();
                continue;
            }
            if (checkYesNo.askAndConfirm(colorOn + "Įrašą norite ištrinti" + colorOff)) {
                deleteRecord();
                continue;
            }
            if (checkYesNo.askAndConfirm(colorOn + "Grįžti" + colorOff)){
                continueEditing = false;
            }
        }
    }

    public void changeRecord(){
        System.out.println(colorOn + "Kurį įrašą redaguosit? " + colorOff);
        try {
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
        } catch (java.util.InputMismatchException e) {
            System.out.println("Neteisinga įvestis. Įveskite sveikąjį skaičių.");
            scanner.nextLine();
        }
    }

    public void deleteRecord(){
        System.out.println(colorOn + "Kurį įrašą norite pašalinti? " + colorOff);
        try {
        int selectedEntry = scanner.nextInt();
        scanner.nextLine();
        Map<Integer, List<CommonRecord>> records = budgetService.getCommonRecords();
        if (records.containsKey(selectedEntry)) {
              records.remove(selectedEntry);
        } else {
            System.out.println("Įrašas " + selectedEntry + " nerastas.");
        }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Neteisinga įvestis. Įveskite sveikąjį skaičių.");
            scanner.nextLine();
        }
    }

    public void incomeRecordChange(IncomeRecord incomeRecord) {
        final String colorOn = "\033[32m";
        final String colorOff = "\033[0m";
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti pajamų sumą" + colorOff)){
                System.out.println(colorOn + "Įveskite naują pajamų sumą: ");
            try {
                BigDecimal incomeAmount = scanner.nextBigDecimal();
                incomeRecord.setAmount(incomeAmount);
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Neteisinga įvestis. Įveskite tik skaičių.");
                scanner.nextLine();
            }
        }
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti pajamų tipą" + colorOff)) {
            IncomeType incomeType = meniuIncome.fillIncomeRecordTypeField();
            incomeRecord.setIncomeType(incomeType);
        }
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti pajamų kategoriją" + colorOff)) {
            IncomeCategory incomeCategory = meniuIncome.fillIncomeRecordCategoryField();
            incomeRecord.setIncomeCategory(incomeCategory);
        }
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti komentarą" + colorOff)) {
            System.out.println(colorOn + "Komentaras(iki 20 simbolių): " + colorOff);
            String comment = scanner.nextLine();
            comment = comment.substring(0, Math.min(comment.length(), 20));
            incomeRecord.setOtherInfo(comment);
        }
    }

    public void expenseRecordChange(ExpenseRecord expenseRecord) {
        final String colorOn = "\033[31m";
        final String colorOff = "\033[0m";
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti išlaidų sumą" + colorOff)) {
                System.out.println(colorOn + "Įveskite naują išlaidų sumą: " + colorOff);
            try {
                BigDecimal expenseAmount = scanner.nextBigDecimal();
                expenseRecord.setAmount(expenseAmount);
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Neteisinga įvestis. Įveskite tik skaičių.");
                scanner.nextLine();
            }
        }
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti išlaidų tipą" + colorOff)) {
            ExpenseType expenseType = meniuExpense.fillExpenceRecordTypeField();
            expenseRecord.setExpenseType(expenseType);
        }
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti išlaidų kategoriją" + colorOff)) {
            ExpenseCategory expenseCategory = meniuExpense.fillExpenceRecordCategoryField();
            expenseRecord.setExpenseCategory(expenseCategory);
        }
        if (checkYesNo.askAndConfirm(colorOn + "Ar norite pakeisti komentarą" + colorOff)) {
            System.out.println(colorOn + "Komentaras(iki 20 simbolių): " + colorOff);
            String comment = scanner.nextLine();
            comment = comment.substring(0, 20);
            expenseRecord.setOtherInfo(comment);
        }
    }
}
