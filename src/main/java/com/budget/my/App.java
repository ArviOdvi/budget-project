package com.budget.my;

import java.util.Scanner;

public class App {



    public static void main(String[] args) {
        // BudgetService budgetService = new BudgetService();
        Scanner scanner = new Scanner(System.in);

        Meniu meniu = new Meniu(scanner);
        meniu.meniu();

    }

}