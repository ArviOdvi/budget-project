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

    @BeforeEach
    void setUp() {
        budgetService = new BudgetService();
        budget = new Budget(budgetService);
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testBalancePositive() {
        List<CommonRecord> incomes = new ArrayList<>();
        incomes.add(new IncomeRecord("1", new BigDecimal("1000"), LocalDateTime.now(), "", IncomeCategory.OTHERS, IncomeType.BANK_TRANSFER));
        List<CommonRecord> expenses = new ArrayList<>();
        expenses.add(new ExpenseRecord("2", new BigDecimal("500"), LocalDateTime.now(), "", ExpenseCategory.FOOD_EXPENSES, ExpenseType.CASH));

        Map<Integer, List<CommonRecord>> records = new HashMap<>();
        records.put(1, incomes);
        records.put(2, expenses);

        budgetService.setCommonRecords(records);
        budget.balance();
        Assertions.assertEquals("Balansas: 500\n", outputStreamCaptor.toString());
    }

    @Test
    void testBalanceNegative() {
        List<CommonRecord> incomes = new ArrayList<>();
        incomes.add(new IncomeRecord("1", new BigDecimal("500"), LocalDateTime.now(), "", IncomeCategory.OTHERS, IncomeType.BANK_TRANSFER));
        List<CommonRecord> expenses = new ArrayList<>();
        expenses.add(new ExpenseRecord("2", new BigDecimal("1000"), LocalDateTime.now(), "", ExpenseCategory.FOOD_EXPENSES, ExpenseType.CASH));

        Map<Integer, List<CommonRecord>> records = new HashMap<>();
        records.put(1, incomes);
        records.put(2, expenses);

        budgetService.setCommonRecords(records);
        budget.balance();
        Assertions.assertEquals("Balansas: -500\n", outputStreamCaptor.toString());
    }

    @Test
    void testBalanceZero() {
        List<CommonRecord> incomes = new ArrayList<>();
        incomes.add(new IncomeRecord("1", new BigDecimal("1000"), LocalDateTime.now(), "", IncomeCategory.OTHERS, IncomeType.BANK_TRANSFER));
        List<CommonRecord> expenses = new ArrayList<>();
        expenses.add(new ExpenseRecord("2", new BigDecimal("1000"), LocalDateTime.now(), "", ExpenseCategory.FOOD_EXPENSES, ExpenseType.CASH));

        Map<Integer, List<CommonRecord>> records = new HashMap<>();
        records.put(1, incomes);
        records.put(2, expenses);

        budgetService.setCommonRecords(records);
        budget.balance();
        Assertions.assertEquals("Balansas: 0\n", outputStreamCaptor.toString());
    }
}
