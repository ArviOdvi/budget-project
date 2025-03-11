package com.budget.my;

import com.budget.my.print.PrintExpensesAmount;
import com.budget.my.print.PrintIncomesAmount;

public class Budget {
    private final PrintIncomesAmount printIncomesAmount;
    private final PrintExpensesAmount printExpensesAmount;

    public Budget(BudgetService budgetService) {
        this.printIncomesAmount = new PrintIncomesAmount(budgetService);
        this.printExpensesAmount = new PrintExpensesAmount(budgetService);
    }
    public void balance(){
       System.out.println("Balansas: " + printIncomesAmount.printIncomesAmount().subtract(printExpensesAmount.printExpensesAmount()) + "\n");
    }
}
