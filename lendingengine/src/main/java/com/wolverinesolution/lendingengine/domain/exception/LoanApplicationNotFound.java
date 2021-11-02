package com.wolverinesolution.lendingengine.domain.exception;

public class LoanApplicationNotFound extends RuntimeException{

    public LoanApplicationNotFound(long loanApplicationId) {
        super("Loan Application with id:"+loanApplicationId+" is not found.");
    }
}
