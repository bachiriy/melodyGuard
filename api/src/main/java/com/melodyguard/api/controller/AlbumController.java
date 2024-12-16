package com.melodyguard.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.melodyguard.api.dto.album.AlbumRequest;
import com.melodyguard.api.dto.album.AlbumResponse;
import com.melodyguard.api.service.AlbumService;

@RestController
@RequestMapping("/api/user")
public class AlbumController {
    @Autowired AlbumService service;


    @GetMapping("/albums")
    public List<AlbumResponse> getALLBums(){
        return service.getAllAlbums();
    }

    @GetMapping("/albums/{id}")
    public AlbumResponse getAlbum(@PathVariable("id") String albumId){
        return service.getAlbumById(albumId);
    }


    @PostMapping("/albums")
    public AlbumResponse postAlbum(@Valid @RequestBody AlbumRequest albumRequest){
        return service.createAlbum(albumRequest);
    }

    @PatchMapping("/albums/{id}")
    public AlbumResponse patchAlbum(@PathVariable("id") String albumId, @RequestBody AlbumRequest albumRequest){
        return service.updateAlbum(albumId, albumRequest);
    }


    @DeleteMapping("/admin/albums/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable("id") String albumId){
        return service.delete(albumId);
    }
}
