package com.melodyguard.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.melodyguard.api.dto.role.RoleRequest;
import com.melodyguard.api.dto.user.UserResponse;
import com.melodyguard.api.entity.User;
import com.melodyguard.api.repository.UserRepository;
import com.melodyguard.api.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired private UserService service;
    @Autowired private UserRepository userRepo;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id){
        return service.getUserById(id);
    }

    @PostMapping("/users/{id}/roles")
    public User postUserRoles(@PathVariable("id") String userId, @Valid @RequestBody RoleRequest roleRequest){
        return service.updateUserRoles(userId, roleRequest);
    }


    @GetMapping("/me")
    public User getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findByEmail(email).get();
        return user;
    }
}
