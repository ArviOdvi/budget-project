package com.budget.my;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IncomeRecord {
    private final BigDecimal amount;
    private final String category;
    private final LocalDateTime date;
    private final boolean isBankTransfer;
    private final String otherInfo;

    public IncomeRecord(BigDecimal amount, String category, LocalDateTime date, boolean isBankTransfer, String otherInfo) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.isBankTransfer = isBankTransfer;
        this.otherInfo = otherInfo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" +
                "amount=" + amount +
                '}';
    }
}
