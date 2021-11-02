package com.rishabh.securityapp.user.repository;

import com.rishabh.securityapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
