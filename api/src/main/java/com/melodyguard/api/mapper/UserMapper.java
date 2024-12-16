package com.melodyguard.api.mapper;

import org.mapstruct.Mapper;

import com.melodyguard.api.dto.user.UserRequest;
import com.melodyguard.api.dto.user.UserResponse;
import com.melodyguard.api.entity.User;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toDto(User user);
}
