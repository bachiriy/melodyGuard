package com.melodyguard.api.dto.user;

import java.util.List;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    String id;
    String email;
    String username;
    List<String> roles;
    String last_active;
}
