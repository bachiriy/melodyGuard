package com.melodyguard.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.melodyguard.api.entity.Song;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
    boolean existsByTitle(String title);
}
