package com.artport.artport.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
}
