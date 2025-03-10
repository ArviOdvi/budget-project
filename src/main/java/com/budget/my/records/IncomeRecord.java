package com.budget.my.records;

import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.enum_data.IncomeType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
public class IncomeRecord extends CommonRecord {

    private IncomeCategory incomeCategory;
    private IncomeType incomeType;

    public IncomeRecord(String id, BigDecimal amount, LocalDateTime date, String otherInfo, IncomeCategory incomeCategory, IncomeType incomeType) {
        super(id, amount, date, otherInfo);

         if (incomeCategory == null || incomeCategory.toString().isBlank()) {
            throw new IllegalArgumentException("Pajamos kategorija negali būti tuščia.");
        }
        if (incomeType == null || incomeType.toString().isBlank()) {
            throw new IllegalArgumentException("Pajamų tipas negali būti tuščias.");
        }

        this.incomeCategory = incomeCategory;
        this.incomeType = incomeType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hash(incomeCategory);
        result = 31 * result + Objects.hash(incomeType);
        return result;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", date=" + getDate() +
                ", otherInfo='" + getOtherInfo() + '\'' +
                ", incomeCategory='" + incomeCategory + '\'' +
                ", incomeType='" + incomeType + '\'' +
                '}';
    }
}
