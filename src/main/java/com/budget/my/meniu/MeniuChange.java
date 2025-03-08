package com.budget.my.meniu;

import com.budget.my.BudgetService;
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

    private final PrintRecords printRecords;
    public MeniuChange(BudgetService budgetService, Scanner scanner) {
        this.budgetService = budgetService;
        this.scanner = scanner;
        this.printRecords = new PrintRecords(budgetService);
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
                        continueEditing = false;;
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
                CommonRecord record = recordList.get(0); // Gauname pirmą įrašą iš sąrašo
                if (record instanceof IncomeRecord) {
                    incomeRecordChange(selectedEntry, (IncomeRecord) record);
                } else if (record instanceof ExpenseRecord) {
                    expenseRecordChange(selectedEntry, (ExpenseRecord) record);
                }
            } else {
                System.out.println("Įrašų sąrašas tuščias.");
            }
        } else {
            System.out.println("Įrašas su raktu " + selectedEntry + " nerastas.");
        }
//        IncomeCategory incomeCategory;
//        switch (categoryChoice) {
//            case 1:
//                incomeCategory = IncomeCategory.SALARY;
//                break;
//            case 2:
//                incomeCategory = IncomeCategory.DIVIDENDS;
//                break;
//            case 3:
//                incomeCategory = IncomeCategory.TAX_RETURN;
//                break;
//            case 4:
//                incomeCategory = IncomeCategory.OTHERS;
//                break;
//            default:
//                System.out.println("Neteisingas pasirinkimas. Naudojama numatytoji kategorija: OTHERS");
//                incomeCategory = IncomeCategory.OTHERS;
//                break;
//        }
//        System.out.println("Jūsų komentaras: ");
//        String comment = scanner.nextLine();
//        int counter = budgetService.getCounter() + 1;
//        budgetService.setCounter(counter);
//        final CommonRecord incomeCommonRecord = new IncomeRecord(id, amount,
//                LocalDateTime.now(), comment, incomeCategory, incomeType);
//        Map<Integer, List<CommonRecord>> incomeRecords = budgetService.getCommonRecords();
//        incomeRecords.putIfAbsent(counter, new ArrayList<>());
//        incomeRecords.get(counter).add(incomeCommonRecord);
    }
    public void deleteRecord(){
        // Pajamų kategorijos pasirinkimas
//        System.out.println("Pasirinkite pajamų kategoriją:");
//        System.out.println("1. Atlyginimas");
//        System.out.println("2. Dividendai");
//        System.out.println("3. Mokesčių grąžinimas");
//        System.out.println("4. Kita");
//        int categoryChoice = scanner.nextInt();
//        scanner.nextLine();
//
//        IncomeCategory incomeCategory;
//        switch (categoryChoice) {
//            case 1:
//                incomeCategory = IncomeCategory.SALARY;
//                break;
//            case 2:
//                incomeCategory = IncomeCategory.DIVIDENDS;
//                break;
//            case 3:
//                incomeCategory = IncomeCategory.TAX_RETURN;
//                break;
//            case 4:
//                incomeCategory = IncomeCategory.OTHERS;
//                break;
//            default:
//                System.out.println("Neteisingas pasirinkimas. Naudojama numatytoji kategorija: OTHERS");
//                incomeCategory = IncomeCategory.OTHERS;
//                break;
//        }
//        System.out.println("Jūsų komentaras: ");
//        String comment = scanner.nextLine();
//        int counter = budgetService.getCounter() + 1;
//        budgetService.setCounter(counter);
//        final CommonRecord incomeCommonRecord = new IncomeRecord(id, amount,
//                LocalDateTime.now(), comment, incomeCategory, incomeType);
//        Map<Integer, List<CommonRecord>> incomeRecords = budgetService.getCommonRecords();
//        incomeRecords.putIfAbsent(counter, new ArrayList<>());
//        incomeRecords.get(counter).add(incomeCommonRecord);
    }
    public void incomeRecordChange(int selectedEntry, IncomeRecord incomeRecord) {
        BigDecimal incomeAmount = BigDecimal.ZERO;
        System.out.println("Ar norite pakeisti pajamų sumą T/N? ");
        String recordCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(recordCorrect)) {
            System.out.println("Įveskite naują pajamų sumą: ");
            incomeAmount = scanner.nextBigDecimal();
            incomeRecord.setAmount(incomeAmount);
            scanner.nextLine();
        }
    }
    public void expenseRecordChange(int selectedEntry, ExpenseRecord expenseRecord) {
        BigDecimal expenseAmount = BigDecimal.ZERO;
        System.out.println("Ar norite pakeisti išlaidų sumą T/N? ");
        scanner.nextLine();
        String recordCorrect = scanner.nextLine();
        if ("T".equalsIgnoreCase(recordCorrect)) {
            System.out.println("Įveskite naują išlaidų sumą: ");
            expenseAmount = scanner.nextBigDecimal();
            expenseRecord.setAmount(expenseAmount);
        }
    }
}
