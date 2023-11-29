package com.artport.artport.controllers;

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
import com.artport.artport.entities.Post;
import com.artport.artport.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public ResponseEntity<List<Post>> getPosts() {
		List<Post> posts = postService.getPosts();
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) {
		PostDTO postDto = postService.getPost(postId);
		if (postDto != null)
			return ResponseEntity.ok(postDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
		try {
			Post updatedPost = postService.updatePost(postId, post);
			return ResponseEntity.ok(updatedPost);
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
