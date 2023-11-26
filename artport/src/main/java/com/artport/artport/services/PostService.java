package com.artport.artport.services;

import java.util.List;

import com.artport.artport.domain.entities.Post;

public interface PostService {

	List<Post> getPosts();
	
	Post getPost(Long postId);

	Post createPost(Post post);
	
	Post updatePost(Long postId, Post post);
	
	void deletePost(Long postId);
}
