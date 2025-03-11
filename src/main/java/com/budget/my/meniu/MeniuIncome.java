package com.budget.my.meniu;

import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.utils.UniqueIdGenerator;
import com.budget.my.enum_data.IncomeType;
import com.budget.my.records_setting.CommonRecord;
import com.budget.my.BudgetService;
import com.budget.my.records_setting.IncomeRecord;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


public class MeniuIncome {
    private final Scanner scanner;
    private final BudgetService budgetService;
    private final String colorOn = "\033[32m";
    private final String colorOff = "\033[0m";

    public MeniuIncome(Scanner scanner, BudgetService budgetService) {
        this.scanner = scanner;
        this.budgetService = budgetService;
    }

    public void fillIncomeRecord() {
        BigDecimal amount = BigDecimal.ZERO;
        String id = UniqueIdGenerator.generateUniqueId();
        System.out.print(colorOn + "Prašome įvesti pajamas EUR: " + colorOff);
        try {
        amount = scanner.nextBigDecimal();
        } catch (NumberFormatException e) {
            System.out.println("Neteisinga įvestis. Įveskite tik skaičių.");
            scanner.nextLine();
        }
        IncomeType incomeType = fillIncomeRecordTypeField();
        IncomeCategory incomeCategory = fillIncomeRecordCategoryField();
        System.out.print(colorOn + "Jūsų komentaras(iki 20 simbolių): " + colorOff);
        String comment = scanner.nextLine();
        comment = comment.substring(0, Math.min(comment.length(), 20));
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
                    colorOn + "++++++++++++++++++++++++++++++++\n" +
                              "+           PAJAMOS            +\n" +
                              "++++++++++++++++++++++++++++++++\n" +
                              "+       1 Į sąskaitą           +\n" +
                              "+       2 Grynais              +\n" +
                              "++++++++++++++++++++++++++++++++\n" + colorOff);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        return IncomeType.BANK_TRANSFER;
                    case 2:
                        return IncomeType.CASH;
                    default:
                        System.out.println("Klaida! Prašome įvesti tik skaičius 1 arba 2.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                scanner.next();
            }
        }
    }
    public IncomeCategory fillIncomeRecordCategoryField(){
        int choice;
        while (true) {
            System.out.println(
            colorOn + "++++++++++++++++++++++++++++++++++++\n" +
                      "+      PAJAMŲ KATEGORIJA           +\n" +
                      "++++++++++++++++++++++++++++++++++++\n" +
                      "+       1 Atlyginimas              +\n" +
                      "+       2 Mokesčių grąžinimas      +\n" +
                      "+       3 Dividendai               +\n" +
                      "+       4 Parduoti daiktai         +\n" +
                      "+       5 Kita                     +\n" +
                      "++++++++++++++++++++++++++++++++++++\n" + colorOff);
          try {
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
          } catch (java.util.InputMismatchException e) {
                  System.out.println("Klaida! Prašome įvesti tik sveiką skaičių nuo 1 iki 3.");
                  scanner.next();
          }
        }
    }
}
