package com.melodyguard.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.user.UserRequest;
import com.melodyguard.api.dto.user.UserResponse;
import com.melodyguard.api.entity.User;
import com.melodyguard.api.exception.ElementNotFoundException;
import com.melodyguard.api.mapper.UserMapper;
import com.melodyguard.api.repository.UserRepository;

@Service
public class UserService {
    @Autowired UserRepository repository;
    @Autowired UserMapper mapper;

    public List<UserResponse> getAllUsers(){
        return repository.findAll().stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    public UserResponse getUserById(String id){
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ElementNotFoundException("User", id));
    }

    public UserResponse updateUserRoles(UserRequest userRequest){
        
        User updatedUser = repository.save(mapper.toEntity(userRequest));

        return mapper.toDto(updatedUser);
    }


    public ResponseEntity<String> delete(String id){
        getUserById(id); // fails when unvalid id
        repository.deleteById(id);
        return ResponseEntity.ok(String.format("User with Id `%s` deleted successfully.", id));
    }
}
