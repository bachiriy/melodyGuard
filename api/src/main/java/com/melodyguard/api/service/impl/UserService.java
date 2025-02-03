package com.melodyguard.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.melodyguard.api.dto.request.RoleDto;
import com.melodyguard.api.dto.response.UserDtoResp;
import com.melodyguard.api.exception.ResourceNotFoundException;
import com.melodyguard.api.mapper.UserMapper;
import com.melodyguard.api.model.Role;
import com.melodyguard.api.model.RoleEnum;
import com.melodyguard.api.model.User;
import com.melodyguard.api.repository.RoleRepository;
import com.melodyguard.api.repository.UserRepository;
import com.melodyguard.api.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserMapper mapper;

	@Override
	public User getById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
	}

	@Override
	public List<UserDtoResp> getAll(Integer page) {
		int size = 3;

		Pageable pageable = PageRequest.of(page, size);

		List<User> users = repository.findAll(pageable).getContent();
		return mapper.entitiesToDtos(users);
	}

	@Override
	public void assignRole(String id, RoleDto roleDto) {
		User user = getById(id);

		Role role = roleRepository.findByName(RoleEnum.valueOf("ROLE_" + roleDto.getRoleName().toUpperCase()))
				.orElseThrow(
						() -> new ResourceNotFoundException("Role Not Found With This Name: " + roleDto.getRoleName()));

		Optional<Role> checker = user.getRoles().stream().filter(r -> r.getName().equals(role.getName())).findFirst();
		if (checker.isPresent()) {
			throw new RuntimeException(
					"User: " + user.getName() + " already assgined with role " + roleDto.getRoleName());
		}

		user.getRoles().add(role);

		repository.save(user);
	}

}
