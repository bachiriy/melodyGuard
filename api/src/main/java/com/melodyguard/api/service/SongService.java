package com.melodyguard.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.song.SongRequest;
import com.melodyguard.api.dto.song.SongResponse;
import com.melodyguard.api.entity.Role;
import com.melodyguard.api.entity.Song;
import com.melodyguard.api.exception.ElementAlreadyExistException;
import com.melodyguard.api.exception.ElementNotFoundException;
import com.melodyguard.api.exception.UnauthorizedAccessException;
import com.melodyguard.api.mapper.SongMapper;
import com.melodyguard.api.repository.SongRepository;
import com.melodyguard.api.util.AuthorizationAccess;

@Service
public class SongService {
    @Autowired SongRepository repository;
    @Autowired SongMapper mapper;

    public List<SongResponse> getAllSongs(){
        return repository.findAll().stream().map(song -> mapper.toDto(song)).collect(Collectors.toList());
    }

    public SongResponse getSongById(String id){
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ElementNotFoundException("Song", id));
    }

    public SongResponse createSong(SongRequest songRequest){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }
        
        if (!repository.existsByTitle(songRequest.getTitle())) {
            Song toSaveSong = mapper.toEntity(songRequest);
            Song savedSong = repository.save(toSaveSong);

            return mapper.toDto(savedSong);
        } else throw new ElementAlreadyExistException("Song", null);
    }

    public SongResponse updateSong(String songId, SongRequest dto){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        getSongById(songId); // fails if song not found

        Song dbSong = findById(songId);
        Song dtoSong = mapper.toEntity(dto); // TODO: make this less code

        Song updatedSong = repository.save(
            Song.builder()
                .id(songId)
                .duration(dto.getDuration() != null ? dto.getDuration() : dbSong.getDuration())
                .title(dto.getTitle() != null ? dto.getTitle() : dbSong.getTitle())
                .trackNumber(dto.getTrack_number() != null ? dto.getTrack_number() : dbSong.getTrackNumber())
                .album(dtoSong.getAlbum() != null ? dtoSong.getAlbum() : dbSong.getAlbum())
                .build()
        );

        return mapper.toDto(updatedSong);
        
    }

    public ResponseEntity<String> delete(String id){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        getSongById(id); // fails when unvalid id
        repository.deleteById(id);
        return ResponseEntity.ok(String.format("Song with Id `%s` deleted successfully.", id));
    }

    private Song findById(String id){
        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Song", id));
    }
    
}
