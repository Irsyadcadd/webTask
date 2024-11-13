package com.practice.web.service;

import com.practice.web.dto.UserDto;
import com.practice.web.model.entity.user.User;
import com.practice.web.model.repository.RepositoryException;
import com.practice.web.model.repository.UserRepository;

public class UserService {
	
	private final UserRepository userRepository = new UserRepository();
	
	public UserDto findByName(String name) throws RepositoryException {
		User user = userRepository.findByName(name);
		UserDto userDto = new UserDto(
					user.getUsername(),
					user.getPassword(),
					user.getAddress()
				);
				
		return userDto;
	}
	
	public void insertUser(String name, String password, String address) throws RepositoryException {
		userRepository.insertUser(name, password, address);
	}

}
