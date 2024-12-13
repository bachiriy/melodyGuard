package com.melodyguard.api.dto.song;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongRequest {
    String id;

    @NotNull(message = "'title' is required")
    String title;

    @NotNull(message = "'duration' is required.")
    Integer duration;

    @NotNull(message = "'track_number' is required.")
    Integer track_number;

    @NotNull(message = "must be associated with an album, 'album_id' missing.")
    String album_id;

}
