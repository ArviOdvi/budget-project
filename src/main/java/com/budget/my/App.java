package com.budget.my;

import com.budget.my.meniu.Meniu;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws InterruptedException{
        BudgetService budgetService = new BudgetService();
        Scanner scanner = new Scanner(System.in);
        Meniu meniu = new Meniu(scanner, budgetService);
        meniu.meniu();

    }

}