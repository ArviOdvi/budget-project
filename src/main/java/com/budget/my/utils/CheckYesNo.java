package com.budget.my.utils;

import java.util.Scanner;

public record CheckYesNo(Scanner scanner) {

    public boolean askAndConfirm(String question) {
        String entry;
        do {
            System.out.println(question + " (T/N)");
            entry = scanner.nextLine();
            if ("T".equalsIgnoreCase(entry)) {
                return true;
            } else if ("N".equalsIgnoreCase(entry)) {
                return false;
            } else {
                System.out.println("Neteisinga įvestis. Įveskite T arba N.");
            }
        } while (true);
    }
}
