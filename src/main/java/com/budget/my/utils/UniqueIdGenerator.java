package com.budget.my.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniqueIdGenerator {
    public static String generateUniqueId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
}
