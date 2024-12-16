package com.melodyguard.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.user.UserRequest;
import com.melodyguard.api.dto.auth.LoginRequest;
import com.melodyguard.api.dto.auth.TokenResponse;
import com.melodyguard.api.dto.user.UserResponse;
import com.melodyguard.api.entity.User;
import com.melodyguard.api.exception.ElementAlreadyExistException;
import com.melodyguard.api.exception.ElementNotFoundException;
import com.melodyguard.api.mapper.UserMapper;
import com.melodyguard.api.repository.UserRepository;

@Service
public class AuthService {
    @Autowired UserRepository repository;
    @Autowired UserMapper mapper;


    public UserResponse register(UserRequest userRequest){
        if (!repository.existsByEmail(userRequest.getEmail())) {
            User toSaveUser = mapper.toEntity(userRequest);
            User savedUser = repository.save(toSaveUser);
            return mapper.toDto(savedUser);
        } else throw new ElementAlreadyExistException("User", null);
    }

    public TokenResponse login(LoginRequest credentials){
        if (repository.existsByEmail(credentials.getEmail())) {
            User dbUser = repository.findByEmail(credentials.getEmail()).get();
            // handle login


            return TokenResponse.builder()
                .token("user token")
                .user(dbUser.toString()).build();
        } else throw new ElementNotFoundException("User", null);
    }

    public ResponseEntity<String> logout(){
        // handle logout 


        return ResponseEntity.ok(String.format("logout success, token now is no longer valid."));
    }
}
