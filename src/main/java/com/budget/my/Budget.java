package com.budget.my;

import com.budget.my.print.PrintExpenses;
import com.budget.my.print.PrintIncomes;

public class Budget {
    private final PrintIncomes printIncomes;
    private final PrintExpenses printExpenses;

    public Budget(BudgetService budgetService) {
        this.printIncomes = new PrintIncomes(budgetService);
        this.printExpenses = new PrintExpenses(budgetService);
    }
    public void balance(){
       System.out.println("Balansas: " + printIncomes.printIncomes().subtract(printExpenses.printExpenses()) + "\n");
    }
}
