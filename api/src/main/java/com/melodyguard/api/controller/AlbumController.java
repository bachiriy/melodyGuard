package com.melodyguard.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melodyguard.api.dto.album.AlbumRequest;
import com.melodyguard.api.dto.album.AlbumResponse;
import com.melodyguard.api.service.AlbumService;

@RestController
@RequestMapping("/api")
public class AlbumController {
    @Autowired AlbumService service;


    @GetMapping("/albums")
    public List<AlbumResponse> getALLBums(){ // got it ? :)
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
