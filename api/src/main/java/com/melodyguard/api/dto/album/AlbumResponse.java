package com.melodyguard.api.dto.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResponse {
    String id;
    String title;
    String artist;
    Integer year;
}
