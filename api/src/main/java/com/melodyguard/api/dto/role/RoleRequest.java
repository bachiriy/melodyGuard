package com.melodyguard.api.dto.role;

import javax.validation.constraints.NotNull;
import com.melodyguard.api.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    @NotNull(message = "'role' is required.")
    Role role;    
}
