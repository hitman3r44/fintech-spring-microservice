package com.rishabh.security.user.repository;

import com.rishabh.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReporsitory extends JpaRepository<User,Long> {

    Optional<User> findByUserDetails_Username(String username);
}
