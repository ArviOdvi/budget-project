package com.budget.my;

import com.budget.my.enum_data.ExpenseCategory;
import com.budget.my.enum_data.ExpenseType;
import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.enum_data.IncomeType;
import com.budget.my.records.CommonRecord;
import com.budget.my.records.ExpenseRecord;
import com.budget.my.records.IncomeRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetTest {
    private BudgetService budgetService;
    private Budget budget;
    private ByteArrayOutputStream outputStreamCaptor;
    private LocalDateTime fixedDateTime;

    @BeforeEach
    void setUp() {
        budgetService = new BudgetService();
        budget = new Budget(budgetService);
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        fixedDateTime = LocalDateTime.of(2023, 10, 26, 12, 0);
    }

    @Test
    void testBalancePositive() {
        List<CommonRecord> incomes = new ArrayList<>();
        incomes.add(new IncomeRecord("1", new BigDecimal("1000"), fixedDateTime, "", IncomeCategory.OTHERS, IncomeType.BANK_TRANSFER));
        List<CommonRecord> expenses = new ArrayList<>();
        expenses.add(new ExpenseRecord("2", new BigDecimal("500"), fixedDateTime, "", ExpenseCategory.FOOD_EXPENSES, ExpenseType.CASH));

        Map<Integer, List<CommonRecord>> records = new HashMap<>();
        records.put(1, incomes);
        records.put(2, expenses);

        budgetService.setCommonRecords(records);
        budget.balance();
        Assertions.assertEquals("Balansas: 500", outputStreamCaptor.toString().trim());
    }

    @Test
    void testBalanceNegative() {
        List<CommonRecord> incomes = new ArrayList<>();
        incomes.add(new IncomeRecord("1", new BigDecimal("500"), fixedDateTime, "", IncomeCategory.OTHERS, IncomeType.BANK_TRANSFER));
        List<CommonRecord> expenses = new ArrayList<>();
        expenses.add(new ExpenseRecord("2", new BigDecimal("1000"), fixedDateTime, "", ExpenseCategory.FOOD_EXPENSES, ExpenseType.CASH));

        Map<Integer, List<CommonRecord>> records = new HashMap<>();
        records.put(1, incomes);
        records.put(2, expenses);

        budgetService.setCommonRecords(records);
        budget.balance();
        Assertions.assertEquals("Balansas: -500", outputStreamCaptor.toString().trim());
    }

    @Test
    void testBalanceZero() {
        List<CommonRecord> incomes = new ArrayList<>();
        incomes.add(new IncomeRecord("1", new BigDecimal("1000"), fixedDateTime, "", IncomeCategory.OTHERS, IncomeType.BANK_TRANSFER));
        List<CommonRecord> expenses = new ArrayList<>();
        expenses.add(new ExpenseRecord("2", new BigDecimal("1000"), fixedDateTime, "", ExpenseCategory.FOOD_EXPENSES, ExpenseType.CASH));

        Map<Integer, List<CommonRecord>> records = new HashMap<>();
        records.put(1, incomes);
        records.put(2, expenses);

        budgetService.setCommonRecords(records);
        budget.balance();
        Assertions.assertEquals("Balansas: 0", outputStreamCaptor.toString().trim());
    }
}
