package com.budget.my;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseRecord {
    private final BigDecimal amount;
    private final String category;
    private final LocalDateTime date;
    private final PaymentMethodType paymentMethod;
    private final BankCard bankCard;

    public ExpenseRecord(final BigDecimal amount, final String category, final LocalDateTime date, final PaymentMethodType paymentMethod,
                         final BankCard bankCard) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.bankCard = bankCard;
    }
    public ExpenseRecord(final BigDecimal amount, final String category, final LocalDateTime date, final PaymentMethodType paymentMethod) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.bankCard = null;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public PaymentMethodType getPaymentMethod() {
        return paymentMethod;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExpenseRecord{");
        sb.append("amount=").append(amount);
        sb.append(", category='").append(category).append('\'');
        sb.append(", date=").append(date);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", bankCard=").append(bankCard);
        sb.append('}');
        return sb.toString();
    }
}
