package com.budget.my.print;

import com.budget.my.BudgetService;
import com.budget.my.records.CommonRecord;
import com.budget.my.records.ExpenseRecord;
import com.budget.my.records.IncomeRecord;

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
        Map<Integer, List<CommonRecord>> recordsMap = budgetService.getCommonRecords();
        if (recordsMap.isEmpty()) {
            System.out.println("Įrašų nerasta.");
            return;
        } else {
            System.out.println("\033[33m+------------+--------------------+---------------------+-----------------+------------------+---------------------+---------------------+\033[0m");
            System.out.println("\033[33m| Įrašas     |         ID         |        Suma         |      Tipas      |    Kategorija    |    Komentaras       |         Data        |\033[0m");
            System.out.println("\033[33m+------------+--------------------+---------------------+-----------------+------------------+---------------------+---------------------+\033[0m");

            for (Map.Entry<Integer, List<CommonRecord>> entry : recordsMap.entrySet()) {
                Integer key = entry.getKey();
                List<CommonRecord> recordsList = entry.getValue();
                for (CommonRecord record : recordsList) {
                    String formattedDate = record.getDate() != null ? record.getDate().format(formatter) : "null";
                    if(record instanceof IncomeRecord){
                        IncomeRecord incomeRecord = (IncomeRecord) record;
                        System.out.printf("\033[33m| %-10d | %-18s | %-19s | %-15s |%-17s | %-19s | %-13s |\n\033[0m",
                            key, incomeRecord.getId(), incomeRecord.getAmount(), incomeRecord.getIncomeType(),incomeRecord.getIncomeCategory(),incomeRecord.getOtherInfo(),formattedDate);
                    }
                }

            }

            System.out.println("\033[33m+------------+--------------------+---------------------+-----------------+------------------+---------------------+---------------------+\033[0m");
        }
    }
}