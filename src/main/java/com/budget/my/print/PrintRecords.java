package com.budget.my.print;

import com.budget.my.BudgetService;
import com.budget.my.records_setting.CommonRecord;
import com.budget.my.records_setting.ExpenseRecord;
import com.budget.my.records_setting.IncomeRecord;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PrintRecords {
    private final BudgetService budgetService;

    public PrintRecords(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public void printRecords() {
        String tableColorOn = "\033[33m";
        String tableColorOff = "\033[0m";
        Map<Integer, List<CommonRecord>> recordsMap = budgetService.getCommonRecords();
        if (recordsMap.isEmpty()) {
            System.out.println("Įrašų nerasta.");
        } else {
            System.out.println(tableColorOn + "+------------+--------------------+---------------------+-----------------+------------------+---------------------+---------------------+" + tableColorOff);
            System.out.println(tableColorOn + "| Įrašas     |         ID         |        Suma         |      Tipas      |    Kategorija    |    Komentaras       |         Data        |" + tableColorOff);
            System.out.println(tableColorOn + "+------------+--------------------+---------------------+-----------------+------------------+---------------------+---------------------+" + tableColorOff);

            for (Map.Entry<Integer, List<CommonRecord>> entry : recordsMap.entrySet()) {
                Integer key = entry.getKey();
                List<CommonRecord> recordsList = entry.getValue();
                for (CommonRecord record : recordsList) {
                    String formattedDate = record.getDate() != null ? record.getDate().format(formatter) : "null";
                    if(record instanceof IncomeRecord){
                        IncomeRecord incomeRecord = (IncomeRecord) record;
                        System.out.printf(tableColorOn + "| %-10d | %-18s | %-19s | %-15s |%-17s | %-19s | %-13s |\n" + tableColorOff,
                            key, incomeRecord.getId(), incomeRecord.getAmount(), incomeRecord.getIncomeType(),
                                incomeRecord.getIncomeCategory(),incomeRecord.getOtherInfo(),formattedDate);
                    } else {
                        ExpenseRecord expenseRecord = (ExpenseRecord) record;
                        System.out.printf(tableColorOn + "| %-10d | %-18s | %-19s | %-15s |%-17s | %-19s | %-13s |\n" + tableColorOff,
                                key, expenseRecord.getId(), expenseRecord.getAmount().multiply(BigDecimal.valueOf(-1)), expenseRecord.getExpenseType(),
                                expenseRecord.getExpenseCategory(),expenseRecord.getOtherInfo(),formattedDate);
                    }
                }

            }
            System.out.println(tableColorOn + "+------------+--------------------+---------------------+-----------------+------------------+---------------------+---------------------+\n" + tableColorOff);
        }
    }
}