package com.artport.artport.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artport.artport.dto.PostDTO;
import com.artport.artport.dto.UserDTO;
import com.artport.artport.entities.Post;
import com.artport.artport.entities.User;
import com.artport.artport.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public ResponseEntity<List<PostDTO>> getPosts() {
		List<Post> posts = postService.getPosts();
		
		List<PostDTO> postDTOs = new ArrayList<>();
		
		for(Post post : posts) {
			PostDTO postDTO = new PostDTO();
			postDTO.setId(post.getId());
			postDTO.setTitle(post.getTitle());
			postDTO.setDescription(post.getDescription());
			
			User user = post.getUser();
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setUsername(user.getUsername());
			userDTO.setEmail(user.getEmail());
			userDTO.setHierarchy(user.getHierarchy());
			
			postDTO.setUserDTO(userDTO);
		}
		return ResponseEntity.ok(postDTOs);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) {
		try {
			Post post = postService.getPost(postId);
			User user = post.getUser();
			UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getHierarchy());
			PostDTO postDTO = new PostDTO(post.getId(), userDTO, post.getTitle(), post.getDescription());
			return ResponseEntity.ok(postDTO);
		}
		catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @RequestBody Post post) {
		try {
			Post updatedPost = postService.updatePost(postId, post);
			User user = updatedPost.getUser();
			UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getHierarchy());
			PostDTO updatedPostDTO = new PostDTO(updatedPost.getId(), userDTO, updatedPost.getTitle(), updatedPost.getDescription());
			return ResponseEntity.ok(updatedPostDTO);
		}
		catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();		
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
		try {
            boolean deleted = postService.deletePost(postId);
            if (deleted) {
                return ResponseEntity.ok(null);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
		catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
	}
}
