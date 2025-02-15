package com.budget.my;

import java.util.Scanner;

public class Meniu {
    public static int meniu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("-------------------------------\n" +
                    "-           MENIU              -\n" +
                    "-------------------------------\n" +
                    "-     1. Įvesti pajamas       -\n" +
                    "-     2. Įvesti išlaidas      -\n" +
                    "-     3. Visos pajamos        -\n" +
                    "-     4. Visos išlaidos       -\n" +
                    "-     5. Balansas             -\n" +
                    "-     6. Programos pabaiga    -\n" +
                    "-------------------------------\n");
            if (!sc.hasNextInt()) {
                System.out.println("Klaida! Prašome įvesti tik skaičių.");
                sc.next(); // Išvalome neteisingą įvestį
                continue;  // Pereiname prie naujo ciklo
            }
            choice = sc.nextInt();
            if (choice < 1 || choice > 6){
                System.out.println("Klaida! Prašome įvesti tik skaičius nuo 1 iki 6.");
                continue;
            } else {
                    break;
            }
        }
        return choice;
    }
}
