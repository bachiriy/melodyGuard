package com.melodyguard.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.melodyguard.api.dto.request.SongDtoReq;
import com.melodyguard.api.dto.response.SongDtoResp;
import com.melodyguard.api.model.Song;

@Mapper(componentModel = "spring")
public interface SongMapper {
	SongDtoResp entityToDto(Song entity);

	List<SongDtoResp> entitiesToDtos(List<Song> entities);

	Song DtoToentity(SongDtoReq dto);
}
