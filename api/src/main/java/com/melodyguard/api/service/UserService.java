package com.melodyguard.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.role.RoleRequest;
import com.melodyguard.api.dto.user.UserResponse;
import com.melodyguard.api.entity.Role;
import com.melodyguard.api.entity.User;
import com.melodyguard.api.exception.ElementNotFoundException;
import com.melodyguard.api.exception.UnauthorizedAccessException;
import com.melodyguard.api.mapper.UserMapper;
import com.melodyguard.api.repository.UserRepository;
import com.melodyguard.api.util.AuthorizationAccess;

@Service
public class UserService {
    @Autowired UserRepository repository;
    @Autowired UserMapper mapper;

    public List<UserResponse> getAllUsers(){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        return repository.findAll().stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    public User getUserById(String id){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException("User", id));
    }

    public User updateUserRoles(String userId, RoleRequest roleRequest){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        Optional<User> userById = repository.findById(userId);

        if (userById.isPresent()) {
            User user = userById.get();
            user.setRole(roleRequest.getRole());
            return repository.save(user);
        } else throw new ElementNotFoundException("User", userId);
    }


    public ResponseEntity<String> delete(String id){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        getUserById(id); // fails when unvalid id
        repository.deleteById(id);
        return ResponseEntity.ok(String.format("User with Id `%s` deleted successfully.", id));
    }
}
