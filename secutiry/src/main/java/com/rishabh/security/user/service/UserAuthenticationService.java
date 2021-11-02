package com.rishabh.security.user.service;

import com.rishabh.security.user.model.User;
import com.rishabh.security.user.model.UserDetailsImpl;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<Optional<String>> login(String username, String password);
    User findByToken(String token);
}
