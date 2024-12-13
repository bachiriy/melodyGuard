package com.melodyguard.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.album.AlbumRequest;
import com.melodyguard.api.dto.album.AlbumResponse;
import com.melodyguard.api.dto.song.SongResponse;
import com.melodyguard.api.entity.Album;
import com.melodyguard.api.entity.Song;
import com.melodyguard.api.exception.ElementAlreadyExistException;
import com.melodyguard.api.exception.ElementNotFoundException;
import com.melodyguard.api.mapper.AlbumMapper;
import com.melodyguard.api.repository.AlbumRepository;
import com.melodyguard.api.repository.SongRepository;

@Service
public class AlbumService {
    @Autowired AlbumRepository repository;
    @Autowired AlbumMapper mapper;

    @Autowired SongRepository songRepository;

    private static final Logger log = LoggerFactory.getLogger(AlbumService.class);

    public List<AlbumResponse> getAllAlbums() {

       
        List<String> songIds = new ArrayList<String>();

        return repository.findAll().stream()
            .map(album -> mapper.toDto(album))
            .map(album -> {
               
                album.setSongIds(getSongsOfAlbum(album));
                return album;
            })
            .collect(Collectors.toList());
    }

    private List<String> getSongsOfAlbum(AlbumResponse album){
        if(album.getId() == null) log.error("album id null.");

        List<String> songIds = new ArrayList<String>();
        List<Song> songs = songRepository.findAll();
        

        songs.forEach(song -> {
            log.info("\n\n\nINSIDE FOREACH:"+ song.getAlbum().getId() +" \n");
            if (song.getAlbum().getId().equalsIgnoreCase(album.getId())) {
                songIds.add(song.getId());
                log.info("\n\n\nINSIDE IF STATEMENT\n\n");
            }
            log.info("\n\nOUTSIDE IF STATEMENT, AND AT THE END OF FOREACH: "+ album.getId() +"\n\n");
        });
        return songIds;
    }

    public AlbumResponse getAlbumById(String id){
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ElementNotFoundException("Album", id));
    }

    public AlbumResponse createAlbum(AlbumRequest albumRequest){
        if (!repository.existsByTitle(albumRequest.getTitle())) {
            Album savedAlbum = repository.save(mapper.toEntity(albumRequest));
            return mapper.toDto(savedAlbum);
        } else throw new ElementAlreadyExistException("Album", null);
    }

    public AlbumResponse updateAlbum(String albumId, AlbumRequest dto){
        Album savedAlbum = findById(albumId); // fail if invalid id
        Album albumToSave = mapper.toEntity(dto);
        Album updatedAlbum = repository.save(
            Album.builder()
                .id(albumId)
                .artist(albumToSave.getArtist() != null ? albumToSave.getArtist() : savedAlbum.getArtist())
                .title(albumToSave.getTitle() != null ? albumToSave.getTitle() : savedAlbum.getTitle())
                .year(albumToSave.getYear() != null ? albumToSave.getYear() : savedAlbum.getYear())
                // .songs(albumToSave.getSongs() != null ? albumToSave.getSongs() : savedAlbum.getSongs())
                .build()
        );

        return mapper.toDto(updatedAlbum);
        
    }

    // public Album addSongToAlbum(String albumId, Song newSong){
    //     Album dbAlbum = findById(albumId);

    //     List<Song> currentSongs = dbAlbum.getSongs();
    //     currentSongs.add(newSong);

    //     dbAlbum.setSongs(currentSongs);
    //     return repository.save(dbAlbum);        
    // }

    public ResponseEntity<String> delete(String id){
        getAlbumById(id); // fails when unvalid id
        repository.deleteById(id);
        return ResponseEntity.ok(String.format("Album with Id `%s` deleted successfully.", id));
    }

    // must be public, cuz it's used by the song mapper
    public Album findById(String id){
        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Album", id));
    }
    
}
