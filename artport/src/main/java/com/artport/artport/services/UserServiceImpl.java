package com.artport.artport.services;

import java.util.List;
import java.util.Optional;

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
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User originalUser = optionalUser.get();
            // Update the necessary properties of the user object
            originalUser.setUsername(user.getUsername());
            originalUser.setEmail(user.getEmail());
            originalUser.setPassword(user.getPassword());
            originalUser.setHierarchy(user.getHierarchy());
            // Save the updated user object
            // User savedUser = userRepository.save(user);
            // return ResponseEntity.ok(savedUser);
        } else {
            // return ResponseEntity.notFound().build();
        }
        
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
