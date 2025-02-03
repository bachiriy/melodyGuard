package com.melodyguard.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.request.SongDtoReq;
import com.melodyguard.api.dto.response.SongDtoResp;
import com.melodyguard.api.model.Song;

@Service
public interface ISongService {

	Song getById(String id);

	List<SongDtoResp> getAll(Integer page);

	SongDtoResp getDetails(String id);

	SongDtoResp create(SongDtoReq dto);

	SongDtoResp update(SongDtoReq dto, String id);

	void delete(String id);

	List<SongDtoResp> search(String query, Integer page);

}
