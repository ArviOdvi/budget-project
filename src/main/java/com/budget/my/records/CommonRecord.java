package com.budget.my.records;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public abstract class CommonRecord {
    private final String id;
    private BigDecimal amount;
    private final LocalDateTime date;
    @Setter
    private String otherInfo;

    public CommonRecord(String id, BigDecimal amount, LocalDateTime date, String otherInfo) {

        if (amount == null) {
            throw new IllegalArgumentException("Suma negali būti null.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Suma turi būti didesnė už nulį.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Data negali būti null.");
        }

        this.id = id;
        this.amount = amount;
        this.date = date;
        this.otherInfo = otherInfo;
    }

    public void setAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Suma negali būti null.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Suma turi būti didesnė už nulį.");
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", otherInfo='" + otherInfo + '\'' +
                '}';
    }


}
