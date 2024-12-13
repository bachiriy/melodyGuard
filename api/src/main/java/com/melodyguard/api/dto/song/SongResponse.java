package com.melodyguard.api.dto.song;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongResponse {
    String id;
    String title;
    Integer duration;
    Integer track_number;
    String album_id;

    @Override
    public String toString(){
        return String.format("song[id: %d, title: %s, duration: %s]", this.id, this.title, this.duration);
    }
}
