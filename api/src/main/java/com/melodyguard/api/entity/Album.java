package com.melodyguard.api.entity;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
    @Id
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String artist;

    @NotNull
    private Integer year;

    // @ReadOnlyProperty
    // @DocumentReference(lookup="{'publisher':?#{#self._id} }")
    @DBRef
    private List<Song> songs;
}
