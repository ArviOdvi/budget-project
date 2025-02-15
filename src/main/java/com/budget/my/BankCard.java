package com.budget.my;

public class BankCard {
    private final String name;
    private final String number;

    public BankCard(final String bankCardName, final String bankCardNumber) {
        this.name = bankCardName;
        this.number = bankCardNumber;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BankCard{");
        sb.append("name='").append(name).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
