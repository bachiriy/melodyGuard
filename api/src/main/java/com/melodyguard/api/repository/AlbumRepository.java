package com.melodyguard.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.melodyguard.api.entity.Album;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    boolean existsByTitle(String title);

    Page<Album> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Album> findByArtistContainingIgnoreCase(String artist, Pageable pageable);
    Page<Album> findByYear(Integer year, Pageable pageable);

}
