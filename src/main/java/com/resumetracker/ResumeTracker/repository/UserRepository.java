package com.resumetracker.ResumeTracker.repository;

import com.resumetracker.ResumeTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// no annotation needed because this is interface
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
