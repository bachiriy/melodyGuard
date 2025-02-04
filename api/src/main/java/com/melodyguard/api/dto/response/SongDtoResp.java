package com.melodyguard.api.dto.response;

import java.time.LocalDateTime;

import com.melodyguard.api.model.Album;
import lombok.Data;

@Data
public class SongDtoResp {
	private String id;

	private String title;

	private Integer duration;

	private Integer number;

	private Album album;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
