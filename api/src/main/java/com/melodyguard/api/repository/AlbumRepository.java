package com.melodyguard.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.melodyguard.api.entity.Album;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    boolean existsByTitle(String title);
    // Album findById(String id);
}
