package com.rishabh.lendingengine.domain.repository;

import com.rishabh.lendingengine.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
