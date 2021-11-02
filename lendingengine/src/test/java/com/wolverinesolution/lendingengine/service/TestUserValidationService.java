package com.wolverinesolution.lendingengine.service;

import com.wolverinesolution.lendingengine.application.service.TokenValidationService;
import com.wolverinesolution.lendingengine.domain.exception.UserNotFoundException;
import com.wolverinesolution.lendingengine.domain.model.User;
import com.wolverinesolution.lendingengine.domain.repository.UserRepository;
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
