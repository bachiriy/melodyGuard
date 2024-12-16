package com.melodyguard.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.melodyguard.api.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);

    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
