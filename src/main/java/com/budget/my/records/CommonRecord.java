package com.budget.my.records;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class CommonRecord {
    private final String id;
    private BigDecimal amount;
    private final LocalDateTime date;
    private String otherInfo;

    public CommonRecord(String id, BigDecimal amount, LocalDateTime date, String otherInfo) {
        // Validacija: svarbu patikrinti įvesties parametrus
        if (amount == null) {
            throw new IllegalArgumentException("Suma negali būti null.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) { // Tikrinam, ar suma > 0
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

    public String getId() { // Getteris ID laukui
        return id;
    }

    public BigDecimal getAmount() { // Getteris sumos laukui
        return amount;
    }

    public LocalDateTime getDate() { // Getteris datos laukui
        return date;
    }

    public String getOtherInfo() { // Getteris kitos informacijos laukui
        return otherInfo;
    }

    public void setAmount(BigDecimal amount) { // Setteris sumos laukui
        if (amount == null) {
            throw new IllegalArgumentException("Suma negali būti null.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Suma turi būti didesnė už nulį.");
        }
        this.amount = amount;
    }
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public boolean equals(Object o) { // Perrašome equals() metodą
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonRecord commonRecord = (CommonRecord) o;
        return id.equals(commonRecord.id); // Lyginame tik pagal ID
    }

    @Override
    public int hashCode() { // Perrašome hashCode() metodą
        return Objects.hash(id); // Hash kodas pagal ID
    }

    @Override
    public String toString() { // Perrašome toString() metodą
        return "Record{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", otherInfo='" + otherInfo + '\'' +
                '}';
    }


}
