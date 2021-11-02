package com.wolverinesolution.security.user.service;

import com.wolverinesolution.security.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public TokenProvider(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        //Not do anything as of now
    }

    @Override
    protected UserDetails retrieveUser(String s,
                                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        final Object token = usernamePasswordAuthenticationToken.getCredentials();
        return Optional.ofNullable(token)
                .map(String::valueOf)
                .map(userAuthenticationService::findByToken)
                .map(User::getUserDetails).get();
    }
}
