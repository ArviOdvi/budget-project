package com.budget.my.records;

import com.budget.my.enum_data.ExpenseCategory;
import com.budget.my.enum_data.ExpenseType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
public class ExpenseRecord extends CommonRecord {

    private ExpenseCategory expenseCategory;
    private ExpenseType expenseType;

    public ExpenseRecord(String id, BigDecimal amount, LocalDateTime date, String otherInfo, ExpenseCategory expenseCategory, ExpenseType expenseType) {
        super(id, amount, date, otherInfo);

        if (expenseCategory == null) {
            throw new IllegalArgumentException("Pajamos kategorija negali būti tuščia.");
        }
        if (expenseType == null) {
            throw new IllegalArgumentException("Pajamų tipas negali būti tuščias.");
        }
        this.expenseCategory = expenseCategory;
        this.expenseType = expenseType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hash(expenseCategory);
        result = 31 * result + Objects.hash(expenseType);
        return result;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", date=" + getDate() +
                ", otherInfo='" + getOtherInfo() + '\'' +
                ", incomeCategory='" + expenseCategory + '\'' +
                ", incomeType='" + expenseType + '\'' +
                '}';
    }
}
