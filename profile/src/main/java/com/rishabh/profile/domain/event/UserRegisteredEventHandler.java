package com.rishabh.profile.domain.event;

import com.google.gson.Gson;
import com.rishabh.profile.domain.model.User;
import com.rishabh.profile.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {

    private Logger logger = LoggerFactory.getLogger(UserRegisteredEventHandler.class);

    private static final Gson GSON = new Gson();
    private final UserRepository userRepository;

    @Autowired
    public UserRegisteredEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handleUserRegistration(String userDetails){
        User user = GSON.fromJson(userDetails, User.class);
        logger.info("user {} registered.", user.getUsername());
        userRepository.save(user);
    }
}
