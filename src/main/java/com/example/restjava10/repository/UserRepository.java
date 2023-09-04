package com.example.restjava10.repository;


import com.example.restjava10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> getUserByEmail(String email);

   boolean existsByEmail(String email);

}