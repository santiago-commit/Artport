package com.artport.artport.services;

import java.util.List;
import org.springframework.stereotype.Component;

import com.artport.artport.domain.entities.User;
import com.artport.artport.repositories.UserRepository;

@Component
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

}
