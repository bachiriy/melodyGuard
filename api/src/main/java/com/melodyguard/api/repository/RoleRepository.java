package com.melodyguard.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.melodyguard.api.model.Role;
import com.melodyguard.api.model.RoleEnum;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(RoleEnum name);
}
