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
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	//POSTS HANDLING
	@GetMapping("/{id}/posts")
	public void getPosts(@PathVariable Long id) {
		userService.getPostsByUserId(id);
	}
	
	@GetMapping("/{id}/posts/{postId}")
	public void getPostsById(@PathVariable Long id, @PathVariable Long postId) {
		userService.getPostByUserId(id, postId);
	}

	@PostMapping("/{id}/posts")
	public void createPost(@PathVariable Long id, @RequestBody Post post) {
		userService.createPost(id, post);
	}
	
	@PutMapping("/{id}/posts/{postId}")
	public void updatePost(@PathVariable Long id, @PathVariable Long postId, @RequestBody Post post) {
		userService.updatePost(id, postId, post);
	}
	
	@DeleteMapping("/{id}/posts")
	public void deletePosts(@PathVariable Long id) {
		userService.deletePosts(id);
	}

}
