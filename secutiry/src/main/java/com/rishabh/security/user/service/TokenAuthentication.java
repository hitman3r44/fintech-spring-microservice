package com.rishabh.security.user.service;

import com.google.common.collect.ImmutableMap;
import com.rishabh.security.user.model.User;
import com.rishabh.security.user.model.UserDetailsImpl;
import com.rishabh.security.user.repository.UserReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class TokenAuthentication implements UserAuthenticationService{

    private final TokenService tokenService;
    private final UserReporsitory userReporsitory;

    @Autowired
    public TokenAuthentication(TokenService tokenService, UserReporsitory userReporsitory) {
        this.tokenService = tokenService;
        this.userReporsitory = userReporsitory;
    }

    @Override
    public Optional<Optional<String>> login(String username, String password) {
        return Optional.ofNullable(userReporsitory
                .findByUserDetails_Username(username)
                .filter(user->user.getUserDetails().getPassword().equals(password))
                .map(user->tokenService.expiring(ImmutableMap.of("username",username)))
        );
    }

    @Override
    public User findByToken(String token) {
        Map<String,String> result = tokenService.verify(token);
        return userReporsitory.findByUserDetails_Username(result.get("username")).get();
    }
}
