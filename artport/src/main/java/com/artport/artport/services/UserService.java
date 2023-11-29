package com.artport.artport.services;

import java.util.List;

import com.artport.artport.entities.Post;
import com.artport.artport.entities.User;

public interface UserService {
	
	List<User> getUsers();
	
	User getUser(Long userId);

	User createUser(User user);
	
	User updateUser(Long userId, User user);
	
	void deleteUser(Long userId);

	List<Post> getPostsByUserId(Long userId);
	
	Post createPost(Long userId, Post post);
	
	void deletePosts(Long userId);
	
}
