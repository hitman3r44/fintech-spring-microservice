package com.wolverinesolution.securityapp.user.repository;

import com.wolverinesolution.securityapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
