package com.rishabh.lendingengine.application.service.impl;

import com.rishabh.lendingengine.application.service.TokenValidationService;
import com.rishabh.lendingengine.domain.exception.UserNotFoundException;
import com.rishabh.lendingengine.domain.model.User;
import com.rishabh.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenValidationServiceImpl implements TokenValidationService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String  securityContextBasedURL;

    @Autowired
    public TokenValidationServiceImpl(final UserRepository userRepository,
                                      @Value("${security.baseurl}") final String securityContextBasedURL) {
        this.userRepository = userRepository;
        this.securityContextBasedURL = securityContextBasedURL;
    }

    public User validateToken(final String token){
        //make a REST HTTP call to Security microservice
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange(securityContextBasedURL+"/user/validate", HttpMethod.POST,httpEntity,String.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            return userRepository.findById(response.getBody())
                    .orElseThrow(()->new UserNotFoundException(response.getBody()));
        }
        throw new RuntimeException("Invalid Token");

    }
}
