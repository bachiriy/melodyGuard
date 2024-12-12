package com.melodyguard.api.entity;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
    @Id
    String id;

    @NotNull
    String title;

    @NotNull
    String artist;

    @NotNull
    Integer year;
}
