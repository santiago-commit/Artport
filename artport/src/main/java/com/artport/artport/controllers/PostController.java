package com.artport.artport.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artport.artport.domain.entities.Post;
import com.artport.artport.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<Post> getPosts() {
		return postService.getPosts();
	}
	
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable Long postId) {
		return postService.getPost(postId);
	}
	
	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
		return postService.updatePost(postId, post);
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
	}
}
