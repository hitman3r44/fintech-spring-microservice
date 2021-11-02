package com.wolverinesolution.profile.application;

import com.wolverinesolution.profile.domain.model.User;
import com.wolverinesolution.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/profile/")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUserProfiles(){
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public void createNewUser(@RequestBody final User user){
        user.setRegisteredSince(LocalDate.now());
        userRepository.save(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody final User user){
        userRepository.save(user);
    }
//
//    @DeleteMapping("/user/{username}")
//    public void deleteUser(@PathVariable final String username){
//        userRepository.deleteById(username);
//    }

}
