package com.artport.artport.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artport.artport.entities.Post;
import com.artport.artport.repositories.PostRepository;

@Service
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
	public Post getPost(Long postId) {
		//return postRepository.getReferenceById(postId);
		Optional<Post> optionalPost = postRepository.findById(postId);
		if (optionalPost.isPresent())
			return optionalPost.get();
        else
        	throw new NoSuchElementException("Invalid post ID provided");
	}

	@Override
	public Post updatePost(Long postId, Post post) {
		Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post originalPost = optionalPost.get();
            
            if (post.getTitle() != null)
            	originalPost.setTitle(post.getTitle());
            
            if (post.getDescription() != null)
            	originalPost.setDescription(post.getDescription());

            if (isValidPost(originalPost))
                return postRepository.save(originalPost);
            else
                throw new IllegalArgumentException("Invalid post data");
        }
        else
        	throw new NoSuchElementException("Post not found");
	}

	@Override
	public boolean deletePost(Long postId) {
		Optional<Post> postOptional = postRepository.findById(postId);
	    if (postOptional.isPresent()) {
	        postRepository.delete(postOptional.get());
	        return true;
	    } 
	    else
	        throw new NoSuchElementException("Post not found");
	}
	
	private boolean isValidPost(Post post) {
		if (post == null || post.getTitle() == null || post.getDescription() == null || post.getId() == null || post.getUser() == null)
	        return false;
		else if (post.getTitle().isEmpty() || post.getDescription().isEmpty())
	        return false;
		else if (post.getTitle().length() > 50 || post.getDescription().length() > 2000)
	    	return false;

	    return true;
	}

}
