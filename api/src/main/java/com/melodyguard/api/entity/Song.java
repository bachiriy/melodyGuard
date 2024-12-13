package com.melodyguard.api.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song {
    @Id
    private String id;

    @NotNull
    private String title;

    @NotNull
    private Integer duration;

    @NotNull
    @Field("track_number")
    private Integer trackNumber;

    private Album album;

}
