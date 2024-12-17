package com.melodyguard.api.entity;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    @NotNull(message = "'email' is required.")
    private String email;

    @NotNull(message = "'username' is required.")
    private String username;

    @NotNull(message = "'password' is required.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    
    // List<Role> roles;

    @Override
    public String toString(){
        return String.format("user[id: %d, username: %s, email: %s]", id, username, email);
    }
}
