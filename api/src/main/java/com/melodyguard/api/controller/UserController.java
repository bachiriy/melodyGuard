package com.melodyguard.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import com.melodyguard.api.dto.user.UserRequest;
import com.melodyguard.api.dto.user.UserResponse;
import com.melodyguard.api.entity.User;
import com.melodyguard.api.repository.UserRepository;
import com.melodyguard.api.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired UserService service;
    @Autowired private UserRepository userRepo;



    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/{id}/roles")
    public UserResponse postUserRoles(@Valid @RequestBody UserRequest userRequest){
        return service.updateUserRoles(userRequest);
    }


    @GetMapping("/me")
    public User getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email).get();
    }
}
