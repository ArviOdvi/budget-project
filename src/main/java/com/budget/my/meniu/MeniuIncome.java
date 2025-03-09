package com.budget.my.meniu;

import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.utils.UniqueIdGenerator;
import com.budget.my.enum_data.IncomeType;
import com.budget.my.records.CommonRecord;
import com.budget.my.BudgetService;
import com.budget.my.records.IncomeRecord;
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
    public void fillIncomeRecord() {
        String id = UniqueIdGenerator.generateUniqueId();
        System.out.print("\033[32mPrašome įvesti pajamas EUR: \033[0m");
        BigDecimal amount = scanner.nextBigDecimal();
        IncomeType incomeType = fillIncomeRecordTypeField();
        IncomeCategory incomeCategory = fillIncomeRecordCategoryField();
        scanner.nextLine();
        System.out.print("\033[32mJūsų komentaras(iki 20 simbolių): \033[0m");
        String comment = scanner.nextLine();
        comment = comment.substring(0, 20);
        int counter = budgetService.getCounter() + 1;
        budgetService.setCounter(counter);
        final CommonRecord incomeRecord = new IncomeRecord(id, amount,
                LocalDateTime.now(), comment, incomeCategory, incomeType);
        Map<Integer, List<CommonRecord>> incomeRecords = budgetService.getCommonRecords();
        incomeRecords.putIfAbsent(counter, new ArrayList<>());
        incomeRecords.get(counter).add(incomeRecord);
    }
    public IncomeType fillIncomeRecordTypeField() {
        int choice;
        while (true) {
            System.out.println(
                    "\033[32m++++++++++++++++++++++++++++++++\n" +
                            "+           PAJAMOS            +\n" +
                            "++++++++++++++++++++++++++++++++\n" +
                            "+       1 Į sąskaitą           +\n" +
                            "+       2 Grynais              +\n" +
                            "++++++++++++++++++++++++++++++++\n\033[0m");
            if (!scanner.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                scanner.next(); // Išvalome neteisingą įvestį
                continue;  // Pereiname prie naujo ciklo
            }
            choice =   scanner.nextInt();
            switch (choice) {
                case 1: {
                    return IncomeType.BANK_TRANSFER;
                }
                case 2: {
                    return IncomeType.CASH;
                }
                default: {
                    System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 2");
                }
            }
        }
    }
    public IncomeCategory fillIncomeRecordCategoryField(){
        int choice;
          while (true) {
            System.out.println(
            "\033[32m++++++++++++++++++++++++++++++++++++\n" +
                    "+      PAJAMŲ KATEGORIJA           +\n" +
                    "++++++++++++++++++++++++++++++++++++\n" +
                    "+       1 Atlyginimas              +\n" +
                    "+       2 Mokesčių grąžinimas      +\n" +
                    "+       3 Dividendai               +\n" +
                    "+       4 Parduoti daiktai         +\n" +
                    "+       5 Kita                     +\n" +
                    "++++++++++++++++++++++++++++++++++++\n\033[0m");
            if (!scanner.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                scanner.next();
                continue;
            }
            choice =   scanner.nextInt();
            switch (choice) {
                case 1: {
                    return IncomeCategory.SALARY;
                }
                case 2: {
                    return IncomeCategory.TAX_RETURN;
                }
                case 3: {
                    return IncomeCategory.DIVIDENDS;
                }
                case 4: {
                    return IncomeCategory.SOLD_ITEMS;
                }
                case 5: {
                    return IncomeCategory.OTHERS;
                }
                default: {
                    System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 3.");
                }
            }
        }
    }
}
