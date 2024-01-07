package com.artport.artport.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artport.artport.dto.PostDTO;
import com.artport.artport.dto.UserDTO;
import com.artport.artport.entities.Post;
import com.artport.artport.entities.User;
import com.artport.artport.services.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(final UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers() {
		List<UserDTO> userDTOs = userService.getUsers();
		return ResponseEntity.ok(userDTOs);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
                UserDTO userDTO = userService.getUser(userId);
                return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
                UserDTO createdUserDTO = userService.createUser(user);
                return ResponseEntity.ok(createdUserDTO);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {		
                UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);
                return ResponseEntity.ok(updatedUserDTO);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity deleteUser(@PathVariable Long userId) {
                userService.deleteUser(userId);
                return ResponseEntity.ok(null);
	}
	
	//POSTS HANDLING
	@GetMapping("/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPosts(@PathVariable Long userId) {
		List<PostDTO> postDTOs = userService.getPostsByUserId(userId);
		return ResponseEntity.ok(postDTOs);
		
	}

	@PostMapping("/{userId}/posts")
	public ResponseEntity<PostDTO> createPost(
                @PathVariable Long userId, 
                @RequestPart("post") Post post, 
                @RequestPart("file") List<MultipartFile> files
                //, @RequestPart("file_description") String description
        ) {
                PostDTO createdPostDTO = userService.createPost(userId, post, files);
                return ResponseEntity.ok(createdPostDTO);
        }
	
	@DeleteMapping("/{userId}/posts")
	public ResponseEntity deletePosts(@PathVariable Long userId) {
                userService.deletePosts(userId);
                return ResponseEntity.ok(null);
	}
}