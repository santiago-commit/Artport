package com.artport.artport.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artport.artport.dto.PostDTO;
import com.artport.artport.services.PostService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	public PostController(final PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public ResponseEntity<List<PostDTO>> getPosts() {
		List<PostDTO> postDTOs = postService.getPosts();
		return ResponseEntity.ok(postDTOs);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) {
                PostDTO postDTO = postService.getPost(postId);
                return ResponseEntity.ok(postDTO);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
                PostDTO updatedPostDTO = postService.updatePost(postId, postDTO);
                return ResponseEntity.ok(updatedPostDTO);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity deletePost(@PathVariable Long postId) {
                boolean deleted = postService.deletePost(postId);
                if (deleted)
                    return ResponseEntity.ok(null);
                else
                    return ResponseEntity.notFound().build();
	}
}
