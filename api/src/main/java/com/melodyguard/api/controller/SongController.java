package com.melodyguard.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.melodyguard.api.dto.request.SongDtoReq;
import com.melodyguard.api.dto.response.DeleteResp;
import com.melodyguard.api.service.ISongService;

@RestController
@RequestMapping("/api")
public class SongController {

	@Autowired
	private ISongService service;

	@Secured("ROLE_USER")
	@GetMapping("/user/songs")
	public ResponseEntity<?> index(@RequestParam(defaultValue = "0", name = "page") Integer page) {
		return ResponseEntity.ok(service.getAll(page));
	}

	@Secured("ROLE_USER")
	@GetMapping("/user/songs/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		return ResponseEntity.ok(service.getDetails(id));
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/admin/songs")
	public ResponseEntity<?> store(@RequestBody @Valid SongDtoReq dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/admin/songs/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid SongDtoReq dto, @PathVariable String id) {
		return ResponseEntity.ok(service.update(dto, id));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/admin/songs")
	public ResponseEntity<?> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.ok(DeleteResp.builder().message("Song deleted successfully"));
	}

	@Secured("ROLE_USER")
	@GetMapping("/user/songs/search")
	public ResponseEntity<?> searchBy(@RequestParam(name = "q") String query,
			@RequestParam(defaultValue = "0", name = "page") Integer page) {
		return ResponseEntity.ok(service.search(query, page));
	}
}
