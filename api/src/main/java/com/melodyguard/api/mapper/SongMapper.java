package com.melodyguard.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.melodyguard.api.dto.song.SongRequest;
import com.melodyguard.api.dto.song.SongResponse;
import com.melodyguard.api.entity.Song;
import com.melodyguard.api.service.AlbumService;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(source = "track_number", target = "trackNumber")
    @Mapping(source = "album_id", target = "albumId")
    Song toEntity(SongRequest songRequest);

    @Mapping(source = "album.id", target = "album_id")
    @Mapping(source = "trackNumber", target = "track_number")
    SongResponse toDto(Song song);
}
