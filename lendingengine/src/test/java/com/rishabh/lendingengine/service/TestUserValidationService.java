package com.rishabh.lendingengine.service;

import com.rishabh.lendingengine.application.service.TokenValidationService;
import com.rishabh.lendingengine.domain.exception.UserNotFoundException;
import com.rishabh.lendingengine.domain.model.User;
import com.rishabh.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Primary
@Component
public class TestUserValidationService implements TokenValidationService {

    private final UserRepository userRepository;

    @Autowired
    public TestUserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User validateToken(String token) {
        return userRepository.findById(token)
                .orElseThrow(()-> new UserNotFoundException(token));
    }
}
