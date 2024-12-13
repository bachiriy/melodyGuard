package com.melodyguard.api.mapper;

import org.mapstruct.Mapper;

import com.melodyguard.api.dto.album.AlbumRequest;
import com.melodyguard.api.dto.album.AlbumResponse;
import com.melodyguard.api.entity.Album;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    
    Album toEntity(AlbumRequest requestDto);

    // @Mapping(source = "songs", target = "")
    AlbumResponse toDto(Album entity);
}
