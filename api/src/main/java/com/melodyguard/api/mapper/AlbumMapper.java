package com.melodyguard.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.melodyguard.api.dto.request.AlbumDtoReq;
import com.melodyguard.api.dto.response.AlbumDtoResp;
import com.melodyguard.api.model.Album;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
	AlbumDtoResp entityToDto(Album entity);

	List<AlbumDtoResp> entitiesToDtos(List<Album> entities);

	Album DtoToentity(AlbumDtoReq dto);
}
