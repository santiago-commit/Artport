package com.artport.artport.services;

import java.util.List;

import com.artport.artport.domain.entities.Post;

public interface PostService {

	List<Post> getPosts();
	
	Post getPost(Long id);

	Post createPost(Post post);
	
	Post updatePost(Long id, Post post);
	
	void deletePost(Long id);
}
