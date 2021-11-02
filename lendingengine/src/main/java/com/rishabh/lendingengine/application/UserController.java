package com.rishabh.lendingengine.application;

import com.rishabh.lendingengine.application.service.TokenValidationService;
import com.rishabh.lendingengine.application.service.impl.TokenValidationServiceImpl;
import com.rishabh.lendingengine.domain.model.User;
import com.rishabh.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final TokenValidationService tokenValidationService;

    @Autowired
    public UserController(UserRepository userRepository, TokenValidationService tokenValidationService) {
        this.userRepository = userRepository;
        this.tokenValidationService = tokenValidationService;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers(HttpServletRequest request){
        tokenValidationService.validateToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findAll();
    }

}
