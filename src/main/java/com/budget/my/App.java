package com.budget.my;

import com.budget.my.meniu.Meniu;

import java.util.Scanner;

public class App {

    public static void main(String[] args){
        Meniu meniu = new Meniu(new Scanner(System.in), new BudgetService());
        meniu.meniu();

    }
}