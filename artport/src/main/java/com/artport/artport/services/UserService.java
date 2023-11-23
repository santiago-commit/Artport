package com.artport.artport.services;

import java.util.List;

import com.artport.artport.domain.entities.User;

public interface UserService {

	User createUser(User user);
	
	User getUser(Long id);

	List<User> getUsers();
	
}
