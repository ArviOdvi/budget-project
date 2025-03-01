package com.budget.my;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class IncomeRecord extends CommonRecord {
    private String incomeCategory;
    private String incomeType;

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public void setIncomeCategory(String incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public IncomeRecord(int id, BigDecimal amount, LocalDateTime date, String otherInfo, String incomeCategory, String incomeType) {
        super(id, amount, date, otherInfo); // Kreipiamės į tėvinės klasės konstruktorių

        // Validacija naujiems laukams
        if (incomeCategory == null || incomeCategory.isBlank()) {
            throw new IllegalArgumentException("Pajamos kategorija negali būti tuščia.");
        }
        if (incomeType == null || incomeType.isBlank()) {
            throw new IllegalArgumentException("Pajamų tipas negali būti tuščias.");
        }

        this.incomeCategory = incomeCategory;
        this.incomeType = incomeType;
    }

    public String getIncomeCategory() {
        return incomeCategory;
    }

    public String getIncomeType() {
        return incomeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; // Svarbu palyginti ir su tėvinės klasės equals()
        IncomeRecord that = (IncomeRecord) o;
        return Objects.equals(incomeCategory, that.incomeCategory) && Objects.equals(incomeType, that.incomeType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode(); // Svarbu įtraukti ir tėvinės klasės hashCode()
        result = 31 * result + Objects.hash(incomeCategory);
        result = 31 * result + Objects.hash(incomeType);
        return result;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" +
                "id=" + getId() + // Naudojame getter'į iš tėvinės klasės
                ", amount=" + getAmount() +
                ", date=" + getDate() +
                ", otherInfo='" + getOtherInfo() + '\'' +
                ", incomeCategory='" + incomeCategory + '\'' +
                ", incomeType='" + incomeType + '\'' +
                '}';
    }
}
