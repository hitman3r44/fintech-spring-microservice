package com.rishabh.profile.domain.repository;

import com.rishabh.profile.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
