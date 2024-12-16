package com.melodyguard.api.dto.auth;

import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    // String id;
    
    @NotNull(message = "'email' is required.")
    String email;

    @NotNull(message = "'password' is required.")
    String password;
}
