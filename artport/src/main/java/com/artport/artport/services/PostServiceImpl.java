package com.artport.artport.services;

import java.util.List;
import java.util.Optional;

import com.artport.artport.domain.entities.Post;
import com.artport.artport.repositories.PostRepository;

public class PostServiceImpl implements PostService {
	
	PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post getPost(Long id) {
		return postRepository.getReferenceById(id);
	}

	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Long id, Post post) {
		Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post originalPost = optionalPost.get();
            // Update the necessary properties of the post object
            originalPost.setTitle(post.getTitle());
            originalPost.setDescription(post.getDescription());
            // Save the updated post object
            // Post savedPost = postRepository.save(post);
            // return ResponseEntity.ok(savedPost);
        } else {
            // return ResponseEntity.notFound().build();
        }
        
		return postRepository.save(post);
	}

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
