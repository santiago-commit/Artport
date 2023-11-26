package com.artport.artport.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artport.artport.domain.entities.Post;
import com.artport.artport.domain.entities.User;
import com.artport.artport.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User user) {
		return userService.updateUser(userId, user);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
	
	//POSTS HANDLING
	@GetMapping("/{userId}/posts")
	public void getPosts(@PathVariable Long userId) {
		userService.getPostsByUserId(userId);
	}
	
	@GetMapping("/{userId}/posts/{postId}")
	public void getPostsById(@PathVariable Long userId, @PathVariable Long postId) {
		userService.getPostByUserId(userId, postId);
	}

	@PostMapping("/{userId}/posts")
	public void createPost(@PathVariable Long userId, @RequestBody Post post) {
		userService.createPost(userId, post);
	}
	
	@DeleteMapping("/{userId}/posts")
	public void deletePosts(@PathVariable Long userId) {
		userService.deletePosts(userId);
	}

}
