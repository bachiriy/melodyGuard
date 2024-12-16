package com.melodyguard.api.dto.user;

import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    String id;
    
    @NotNull(message = "'email' is required.")
    String email;

    @NotNull(message = "'username' is required.")
    String username;

    @NotNull(message = "'password' is required.")
    String password;
}
