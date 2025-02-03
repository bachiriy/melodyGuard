package com.melodyguard.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.melodyguard.api.model.Song;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
	List<Song> findByTitleLike(String title, Pageable pageable);

}
