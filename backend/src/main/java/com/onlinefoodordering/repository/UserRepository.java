package com.onlinefoodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodordering.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
