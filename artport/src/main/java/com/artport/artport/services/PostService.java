package com.artport.artport.services;

import com.artport.artport.dto.PostDTO;
import java.util.List;

public interface PostService {

	List<PostDTO> getPosts();
	
	PostDTO getPost(Long postId);
	
	PostDTO updatePost(Long postId, PostDTO postDTO);
	
	boolean deletePost(Long postId);
}
