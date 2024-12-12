package com.melodyguard.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.album.AlbumRequest;
import com.melodyguard.api.dto.album.AlbumResponse;
import com.melodyguard.api.entity.Album;
import com.melodyguard.api.exception.ElementAlreadyExistException;
import com.melodyguard.api.exception.ElementNotFoundException;
import com.melodyguard.api.mapper.AlbumMapper;
import com.melodyguard.api.repository.AlbumRepository;

@Service
public class AlbumService {
    @Autowired AlbumRepository repository;
    @Autowired AlbumMapper mapper;

    public List<AlbumResponse> getAllAlbums(){
        return repository.findAll().stream().map(album -> mapper.toDto(album)).collect(Collectors.toList());
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
        getAlbumById(albumId); // fail if invalid id

        Album dbAlbum = findById(albumId);

        Album updatedAlbum = repository.save(
            Album.builder()
                .id(albumId)
                .artist(dto.getArtist() != null ? dto.getArtist() : dbAlbum.getArtist())
                .title(dto.getTitle() != null ? dto.getTitle() : dbAlbum.getTitle())
                .year(dto.getYear() != null ? dto.getYear() : dbAlbum.getYear())
                .build()
        );

        return mapper.toDto(updatedAlbum);
        
    }

    public ResponseEntity<String> delete(String id){
        getAlbumById(id); // fails when unvalid id
        repository.deleteById(id);
        return ResponseEntity.ok(String.format("Album with Id `%s` deleted successfully.", id));
    }

    private Album findById(String id){
        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Album", id));
    }
    
}
