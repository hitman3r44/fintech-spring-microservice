package com.wolverinesolution.security.user.controller;

import com.wolverinesolution.security.user.model.User;
import com.wolverinesolution.security.user.repository.UserReporsitory;
import com.wolverinesolution.security.user.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wolverinesolution.security.user.model.UserDetailsImpl;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserReporsitory userReporsitory;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public UserController(UserReporsitory userReporsitory, UserAuthenticationService userAuthenticationService) {
        this.userReporsitory = userReporsitory;
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDetailsImpl userDetails){
        userReporsitory.save(new User(userDetails));
        return login(userDetails);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDetailsImpl userDetails){
        return userAuthenticationService
                .login(userDetails.getUsername(),userDetails.getPassword())
                .orElseThrow(()->new RuntimeException("Invalid login details"));

    }

}
