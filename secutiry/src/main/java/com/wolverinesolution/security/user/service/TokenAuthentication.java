package com.wolverinesolution.security.user.service;

import com.google.common.collect.ImmutableMap;
import com.wolverinesolution.security.user.model.User;
import com.wolverinesolution.security.user.repository.UserReporsitory;
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
    public Optional<String> login(String username, String password) {
        Optional<User> userDetails = userReporsitory.findByUserDetails_Username(username);

        String generatedJWT = userReporsitory
                .findByUserDetails_Username(username)
                .filter(user->user.getUserDetails().getPassword().equals(password))
                .map(user->tokenService.expiring(ImmutableMap.of("username",username))).get();


        userDetails.get().getUserDetails().setJwtToken(generatedJWT);

        userReporsitory.save(userDetails.get());

        return Optional.of(generatedJWT);

//        return Optional.of(userReporsitory
//                .findByUserDetails_Username(username)
//                .filter(user->user.getUserDetails().getPassword().equals(password))
//                .map(user->tokenService.expiring(ImmutableMap.of("username",username)))
//        );
    }

    @Override
    public User findByToken(String token) {
        Map<String,String> result = tokenService.verify(token);
//        return userReporsitory.findByUserDetails_Username(result.get("username")).get();
        return userReporsitory.findByUserDetails_JwtToken(result.get("jwtToken")).get();
    }
}
