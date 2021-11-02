package com.rishabh.lendingengine.domain.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Objects;

@Entity
public final class LoanApplication {

    @Id
    @GeneratedValue
    private Long id;
    private int amount;
    @ManyToOne
    private User borrower;
    private int repaymentTerm;
    private double interestRate;
    private Status status;

    public LoanApplication() {
    }

    public LoanApplication(int amount, User borrower, int repaymentTerm, double interestRate) {
        this.amount = amount;
        this.borrower = borrower;
        this.repaymentTerm = repaymentTerm;
        this.interestRate = interestRate;
        this.status=Status.ONGOING;
    }

    public Loan acceptLoanApplication(final User lender){
        lender.withdraw(getAmount());
        borrower.topUp(getAmount());
        status = Status.COMPLETED;
        return new Loan(lender, this);
    }

    public Long getId() {
        return id;
    }

    public Money getAmount() {
        return new Money(Currency.USD,amount);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public int getRepaymentTerm() {
        return repaymentTerm;
    }

    public void setRepaymentTerm(int repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplication that = (LoanApplication) o;
        return amount == that.amount && Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(id, that.id) && Objects.equals(borrower, that.borrower) && Objects.equals(repaymentTerm, that.repaymentTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, borrower, repaymentTerm, interestRate);
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", amount=" + amount +
                ", borrower=" + borrower +
                ", repaymentTerm=" + repaymentTerm +
                ", interestRate=" + interestRate +
                '}';
    }
}
