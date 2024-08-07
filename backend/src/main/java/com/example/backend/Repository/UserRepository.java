package com.example.backend.Repository;

import com.example.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{

     boolean existsByUsername(String username);

     boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    User findByUsername(String username);
}
