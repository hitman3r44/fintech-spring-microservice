package com.wolverinesolution.lendingengine.application.model;

import com.wolverinesolution.lendingengine.domain.model.Money;
import com.wolverinesolution.lendingengine.domain.model.User;

import java.util.Objects;

public class LoanApplicationDTO {
    private long id;
    private Money amount;
    private User borrower;
    private int daysToRepay;
    private double interestRate;

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public void setDaysToRepay(int daysToRepay) {
        this.daysToRepay = daysToRepay;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public long getId() {
        return id;
    }

    public Money getAmount() {
        return amount;
    }

    public User getBorrower() {
        return borrower;
    }

    public int getDaysToRepay() {
        return daysToRepay;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "LoanApplicationDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", user=" + borrower +
                ", daysToRepay=" + daysToRepay +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplicationDTO that = (LoanApplicationDTO) o;
        return id == that.id && daysToRepay == that.daysToRepay && Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(amount, that.amount) && Objects.equals(borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, borrower, daysToRepay, interestRate);
    }
}
