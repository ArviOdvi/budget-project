package com.budget.my;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ExpenseRecord extends CommonRecord {
    private String expenseCategory;
    private String expenseType;

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public ExpenseRecord(int id, BigDecimal amount, LocalDateTime date, String otherInfo, String expenseCategory, String expenseType) {
        super(id, amount, date, otherInfo);
        // Validacija naujiems laukams
        if (expenseCategory == null || expenseCategory.isBlank()) {
            throw new IllegalArgumentException("Pajamos kategorija negali būti tuščia.");
        }
        if (expenseType == null || expenseType.isBlank()) {
            throw new IllegalArgumentException("Pajamų tipas negali būti tuščias.");
        }
        this.expenseCategory = expenseCategory;
        this.expenseType = expenseType;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public String getExpenseType() {
        return expenseType;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode(); // Svarbu įtraukti ir tėvinės klasės hashCode()
        result = 31 * result + Objects.hash(expenseCategory);
        result = 31 * result + Objects.hash(expenseType);
        return result;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" +
                "id=" + getId() + // Naudojame getter'į iš tėvinės klasės
                ", amount=" + getAmount() +
                ", date=" + getDate() +
                ", otherInfo='" + getOtherInfo() + '\'' +
                ", incomeCategory='" + expenseCategory + '\'' +
                ", incomeType='" + expenseType + '\'' +
                '}';
    }
}
