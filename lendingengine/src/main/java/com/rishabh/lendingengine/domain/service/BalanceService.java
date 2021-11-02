package com.rishabh.lendingengine.domain.service;

import com.rishabh.lendingengine.domain.exception.UserNotFoundException;
import com.rishabh.lendingengine.domain.model.Money;
import com.rishabh.lendingengine.domain.model.User;
import com.rishabh.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class BalanceService {

    private final UserRepository userRepository;

    @Autowired
    public BalanceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void topUpBalance(final Money money,String authToken){
        User user = findUser(authToken);
        user.topUp(money);
    }

    @Transactional
    public void withdrawBalance(final Money money,String authToken){
        User user = findUser(authToken);
        user.withdraw(money);
    }

    private User findUser(String authToken) {
        return userRepository.findById(authToken)
                .orElseThrow(() -> new UserNotFoundException(authToken));
    }

}
