package com.melodyguard.api.util;


import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import com.melodyguard.api.entity.Role;


public class AuthorizationAccess {

    public static boolean hasRole(Role role){
        boolean has = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().filter(r -> {
            return r.getAuthority().toString().equals(role.toString());
        }).collect(Collectors.toList()).size() > 0;
        return has;
    }
}