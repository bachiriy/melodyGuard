package com.melodyguard.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.request.AlbumDtoReq;
import com.melodyguard.api.dto.response.AlbumDtoResp;
import com.melodyguard.api.model.Album;

@Service
public interface IAlbumService {

	Album getById(String id);

	List<AlbumDtoResp> getAll(Integer page);

	AlbumDtoResp getDetails(String id);

	AlbumDtoResp create(AlbumDtoReq dto);

	AlbumDtoResp update(AlbumDtoReq dto, String id);

	void delete(String id);

	List<AlbumDtoResp> search(String query, String type, Integer page);

	List<AlbumDtoResp> sort(Integer year, Integer page);
}
