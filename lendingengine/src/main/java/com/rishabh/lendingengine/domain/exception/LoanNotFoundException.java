package com.rishabh.lendingengine.domain.exception;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(Long loanId) {
        super("No loan with id:"+loanId+" is present in the system.");
    }
}
