package com.wolverinesolution.security.user.service;

import com.wolverinesolution.security.user.model.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<Optional<String>> login(String username, String password);
    User findByToken(String token);
}
