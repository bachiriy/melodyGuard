package com.melodyguard.api.dto.auth;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {
    String token;
    String user;
    LocalDateTime last_active;
}
