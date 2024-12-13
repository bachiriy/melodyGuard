package com.melodyguard.api.dto.album;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.melodyguard.api.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumRequest {
    String id;
    
    @NotNull(message = "'title' of the album is required.")
    String title;

    @NotNull(message = "'artist' of the album is required.")
    String artist;

    @NotNull(message = "you must include the 'year' of the album")
    Integer year;

    List<Song> songs;
}
