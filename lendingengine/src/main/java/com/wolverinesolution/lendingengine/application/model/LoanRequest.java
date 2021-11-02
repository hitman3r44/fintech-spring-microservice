package com.wolverinesolution.lendingengine.application.model;

import java.util.Objects;


public class LoanRequest {
    private int amount;
    private int daysToRepay;
    private double interestRate;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public int getDaysToRepay() {
        return daysToRepay;
    }

    public void setDaysToRepay(int daysToRepay) {
        this.daysToRepay = daysToRepay;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public LoanRequest(int amount, int daysToRepay, double interestRate) {
        this.amount = amount;
        this.daysToRepay = daysToRepay;
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return amount == that.amount && daysToRepay == that.daysToRepay && Double.compare(that.interestRate, interestRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, daysToRepay, interestRate);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", daysToRepay=" + daysToRepay +
                ", interestRate=" + interestRate +
                '}';
    }
}
