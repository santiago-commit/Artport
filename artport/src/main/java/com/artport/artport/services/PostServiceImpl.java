package com.artport.artport.services;

import java.util.List;

import com.artport.artport.domain.entities.Post;
import com.artport.artport.repositories.PostRepository;

public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post getPost(Long id) {
		return postRepository.getReferenceById(id);
	}

	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

}
