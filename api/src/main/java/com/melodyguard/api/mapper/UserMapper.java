package com.melodyguard.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.melodyguard.api.dto.request.UserDtoReq;
import com.melodyguard.api.dto.response.UserDtoResp;
import com.melodyguard.api.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	UserDtoResp entityToDto(User entity);

	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	List<UserDtoResp> entitiesToDtos(List<User> entities);

	User DtoToentity(UserDtoReq dto);

}
