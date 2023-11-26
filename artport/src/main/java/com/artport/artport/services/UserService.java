package com.artport.artport.services;

import java.util.List;

import com.artport.artport.domain.entities.Post;
import com.artport.artport.domain.entities.User;

public interface UserService {
	
	List<User> getUsers();
	
	User getUser(Long id);

	User createUser(User user);
	
	User updateUser(Long id, User user);
	
	void deleteUser(Long id);


	List<Post> getPostsByUserId(Long id);
	
	Post getPostByUserId(Long id, Long postId);
	
	Post createPost(Long id, Post post);
	
	Post updatePost(Long id, Long postId, Post post);
	
	void deletePosts(Long id);
	
}
