package com.artport.artport.services;

import java.util.List;

import com.artport.artport.domain.dto.PostDTO;
import com.artport.artport.domain.entities.Post;

public interface PostService {

	List<Post> getPosts();
	
	PostDTO getPost(Long postId);
	
	Post updatePost(Long postId, Post post);
	
	boolean deletePost(Long postId);
}
