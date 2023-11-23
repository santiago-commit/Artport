package com.artport.artport.services;

import java.util.List;

import com.artport.artport.domain.entities.Post;

public interface PostService {
	
	Post createPost(Post post);
	
	Post getPost(Long id);

	List<Post> getPosts();
}
