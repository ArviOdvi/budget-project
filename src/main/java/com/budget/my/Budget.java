package com.budget.my;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Budget {
    private final BudgetService budgetService;
    private BigDecimal incomeAmount;
    private BigDecimal expenseAmount;

    public Budget(BudgetService budgetService) {

        this.budgetService = budgetService;
    }

    public void balance(){
       System.out.println("Balansas: " + incomeAmount.subtract(expenseAmount));
    }
}
