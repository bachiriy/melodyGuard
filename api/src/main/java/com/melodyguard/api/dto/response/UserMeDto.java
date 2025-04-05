package com.melodyguard.api.dto.response;

import java.util.Set;
import com.melodyguard.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMeDto {
    private String id;
    private String name;
    private String username;
    private Set<Role> roles;
    private Boolean enable;
} 