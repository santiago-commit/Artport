package com.artport.artport.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artport.artport.domain.dto.PostDTO;
import com.artport.artport.domain.dto.UserDTO;
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
	public ResponseEntity<List<UserDTO>> getUsers() {
		List<User> users = userService.getUsers();
		
		List<UserDTO> userDTOs = new ArrayList<>();

	    for (User user : users) {
	    	UserDTO userDTO = new UserDTO();
	    	userDTO.setId(user.getId());
	    	userDTO.setUsername(user.getUsername());
	    	userDTO.setEmail(user.getEmail());
	    	userDTO.setHierarchy(user.getHierarchy());
	    	userDTOs.add(userDTO);
	    }
	    
		return ResponseEntity.ok(userDTOs);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
		try {
			User user = userService.getUser(userId);
			UserDTO userDto = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getHierarchy());
			return ResponseEntity.ok(userDto);
		}
		catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
		try {
			User createdUser = userService.createUser(user);
			UserDTO createdUserDTO = new UserDTO(createdUser.getId(), createdUser.getUsername(), createdUser.getEmail(), createdUser.getHierarchy());
			return ResponseEntity.ok(createdUserDTO);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody User user) {		
		try {
			User updatedUser = userService.updateUser(userId, user);
			UserDTO updatedUserDTO = new UserDTO(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getHierarchy());
			return ResponseEntity.ok(updatedUserDTO);
		}
		catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();		
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(null);
        }
		catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
	}
	
	//POSTS HANDLING
	@GetMapping("/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPosts(@PathVariable Long userId) {
		List<Post> posts = userService.getPostsByUserId(userId);
		List<PostDTO> postDTOs = new ArrayList<>();

	    for (Post post : posts) {
	    	PostDTO postDTO = new PostDTO();
	    	postDTO.setId(post.getId());
	    	
	    	User user = post.getUser();
	    	UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getHierarchy());
	    	postDTO.setUserDTO(userDTO);
	    	
	    	postDTO.setTitle(post.getTitle());
	    	postDTO.setDescription(post.getDescription());
	    	postDTOs.add(postDTO);
	    }
		return ResponseEntity.ok(postDTOs);
		
	}

	@PostMapping("/{userId}/posts")
	public ResponseEntity<PostDTO> createPost(@PathVariable Long userId, @RequestBody Post post) {
		try {
			Post createdPost = userService.createPost(userId, post);

			User user = createdPost.getUser();
			UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getHierarchy());
			
			PostDTO createdPostDTO = new PostDTO(createdPost.getId(), userDTO, createdPost.getTitle(), createdPost.getDescription());
			return ResponseEntity.ok(createdPostDTO);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
		catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{userId}/posts")
	public ResponseEntity<Void> deletePosts(@PathVariable Long userId) {
		try {
			userService.deletePosts(userId);
            return ResponseEntity.ok(null);
        }
		catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
	}

}
