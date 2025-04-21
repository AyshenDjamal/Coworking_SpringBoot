package com.example.springapi.repository;

import com.example.springapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean findByUsername(String username);  // Changed from boolean to Optional<User>

    User getByUsername(String username);
}