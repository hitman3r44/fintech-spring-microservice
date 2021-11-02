package com.wolverinesolution.profile.domain.repository;

import com.wolverinesolution.profile.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
