package com.budget.my.records;

import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.enum_data.IncomeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class IncomeRecord extends CommonRecord {
    private final IncomeCategory incomeCategory;
    private final IncomeType incomeType;

    public IncomeRecord(String id, BigDecimal amount, LocalDateTime date, String otherInfo, IncomeCategory incomeCategory, IncomeType incomeType) {
        super(id, amount, date, otherInfo); // Kreipiamės į tėvinės klasės konstruktorių

        // Validacija naujiems laukams
        if (incomeCategory == null || incomeCategory.toString().isBlank()) {
            throw new IllegalArgumentException("Pajamos kategorija negali būti tuščia.");
        }
        if (incomeType == null || incomeType.toString().isBlank()) {
            throw new IllegalArgumentException("Pajamų tipas negali būti tuščias.");
        }

        this.incomeCategory = incomeCategory;
        this.incomeType = incomeType;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public IncomeType getIncomeType() {
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
