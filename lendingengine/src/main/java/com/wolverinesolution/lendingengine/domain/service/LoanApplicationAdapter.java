package com.wolverinesolution.lendingengine.domain.service;

import com.wolverinesolution.lendingengine.application.model.LoanRequest;
import com.wolverinesolution.lendingengine.domain.exception.UserNotFoundException;
import com.wolverinesolution.lendingengine.domain.model.LoanApplication;
import com.wolverinesolution.lendingengine.domain.model.User;
import com.wolverinesolution.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoanApplicationAdapter {
    @Autowired
    private final UserRepository userRepository;

    public LoanApplicationAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoanApplication transform(LoanRequest loanRequest,User borrower){
        Optional<User> userOptional = userRepository.findById(borrower.getUsername());
        if(userOptional.isPresent()){
            return new LoanApplication(loanRequest.getAmount(),
                    userOptional.get(),
                    loanRequest.getDaysToRepay(),
                    loanRequest.getInterestRate());
        }else{
            throw new UserNotFoundException(borrower.getUsername());
        }
        //new LoanApplication(loanRequest.get)
    }
}
