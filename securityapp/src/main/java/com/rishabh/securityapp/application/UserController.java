package com.rishabh.securityapp.application;

import com.rishabh.securityapp.user.dto.UserDTO;
import com.rishabh.securityapp.user.exception.UserNotFoundException;
import com.rishabh.securityapp.user.model.User;
import com.rishabh.securityapp.user.repository.UserRepository;
import com.rishabh.securityapp.user.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Autowired
    public UserController(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO){
        User user = new User(userDTO.getUsername(),userDTO.getPassword());
        this.userRepository.save(user);
        this.notificationService.sendMessage(userDTO);
    }


    @PostMapping("/validate")
    public String getValidUsername(@RequestHeader("Authorization") String token){
        return userRepository.findById(token)
                .orElseThrow(UserNotFoundException::new)
                .getUsername();
    }
}
