package com.melodyguard.api.dto.user;

import com.melodyguard.api.entity.Role;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    String id;
    String email;
    String username;
    Role role;
}
