package com.melodyguard.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.melodyguard.api.dto.song.*;
import com.melodyguard.api.service.SongService;

@RestController
@RequestMapping("/api")
public class SongController {
    @Autowired SongService service;


    @GetMapping("/songs")
    public List<SongResponse> getAllSongs(){
        return service.getAllSongs();
    }

    @GetMapping("/songs/{id}")
    public SongResponse getSong(@PathVariable("id") String songId){
        return service.getSongById(songId);
    }


    @PostMapping("/songs")
    public SongResponse postSong(@Valid @RequestBody SongRequest songRequest){
        return service.createSong(songRequest);
    }

    @PatchMapping("/songs/{id}")
    public SongResponse patchSong(@PathVariable("id") String songId, @RequestBody SongRequest songRequest){
        return service.updateSong(songId, songRequest);
    }


    @DeleteMapping("/admin/songs/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable("id") String songId){
        return service.delete(songId);
    }
}
