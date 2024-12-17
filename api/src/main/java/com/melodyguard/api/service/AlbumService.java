package com.melodyguard.api.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.album.*;
import com.melodyguard.api.entity.Album;
import com.melodyguard.api.entity.Role;
import com.melodyguard.api.entity.Song;
import com.melodyguard.api.exception.*;
import com.melodyguard.api.mapper.AlbumMapper;
import com.melodyguard.api.repository.AlbumRepository;
import com.melodyguard.api.repository.SongRepository;
import com.melodyguard.api.util.AuthorizationAccess;

@Service
public class AlbumService {
    @Autowired AlbumRepository repository;
    @Autowired AlbumMapper mapper;

    @Autowired SongRepository songRepository;

    private static final Logger log = LoggerFactory.getLogger(AlbumService.class);

    public Page<AlbumResponse> getAllAlbums(int page, int size, String title, String artist, Integer year) {

        Pageable paging = PageRequest.of(page - 1, size);
        
        Page<Album> albumPages;
        if (title != null) {
            albumPages = repository.findByTitleContainingIgnoreCase(title, paging);
        } else if (artist != null) {
            logg(artist);
            albumPages = repository.findByArtistContainingIgnoreCase(artist, paging);
        } else if (year != null) {
            albumPages = repository.findByYear(year, paging);
        } else {
            albumPages = repository.findAll(paging);
        }
        
        return albumPages
        .map(album -> mapper.toDto(album))
        .map(album -> {
            album.setSongIds(getSongsOfAlbum(album));
                return album;
            }
        );
    }

    private void logg(Object obj){
        log.info("\n\n" + 
        obj
        + "\n\n");
    }

    public AlbumResponse getAlbumById(String id){
        AlbumResponse albumResponse = repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ElementNotFoundException("Album", id));
        albumResponse.setSongIds(getSongsOfAlbum(albumResponse));
        return albumResponse;
    }

    public AlbumResponse createAlbum(AlbumRequest albumRequest){
        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }
        
        if (!repository.existsByTitle(albumRequest.getTitle())) {
            Album savedAlbum = repository.save(mapper.toEntity(albumRequest));
            return mapper.toDto(savedAlbum);
        } else throw new ElementAlreadyExistException("Album", null);
    }

    public AlbumResponse updateAlbum(String albumId, AlbumRequest dto){

        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        Album savedAlbum = findById(albumId); // fail if invalid id
        Album albumToSave = mapper.toEntity(dto);
        Album updatedAlbum = repository.save(
            Album.builder()
                .id(albumId)
                .artist(albumToSave.getArtist() != null ? albumToSave.getArtist() : savedAlbum.getArtist())
                .title(albumToSave.getTitle() != null ? albumToSave.getTitle() : savedAlbum.getTitle())
                .year(albumToSave.getYear() != null ? albumToSave.getYear() : savedAlbum.getYear())
                .build()
        );

        return mapper.toDto(updatedAlbum);   
    }

    public ResponseEntity<String> delete(String id){

        if (!AuthorizationAccess.hasRole(Role.ADMIN)) {
            throw new UnauthorizedAccessException("Unauthorized Access, only admins can perform this action.");
        }

        getAlbumById(id); // fails when unvalid id
        repository.deleteById(id);
        return ResponseEntity.ok(String.format("Album with Id `%s` deleted successfully.", id));
    }

    public Album findById(String id){
        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Album", id));
    }

    private List<String> getSongsOfAlbum(AlbumResponse album){
        if(album.getId() == null) log.error("album id null.");

        List<String> songIds = new ArrayList<String>();
        List<Song> songs = songRepository.findAll();

        songs.forEach(song -> {
            if (song.getAlbum().getId().equalsIgnoreCase(album.getId())) {
                songIds.add(song.getId());
            }
        });
        return songIds;
    }
    
}
