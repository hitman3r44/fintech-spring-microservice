package com.wolverinesolution.lendingengine.domain.repository;

import com.wolverinesolution.lendingengine.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
