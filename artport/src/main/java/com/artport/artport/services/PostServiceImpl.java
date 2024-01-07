package com.artport.artport.services;

import com.artport.artport.dto.PostDTO;
import com.artport.artport.dto.PostDTOMapper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artport.artport.entities.Post;
import com.artport.artport.repositories.PostRepository;
import java.util.ArrayList;

@Service
public class PostServiceImpl implements PostService {
	
	final PostRepository postRepository;
        final PostDTOMapper postDTOMapper;

	public PostServiceImpl(final PostRepository postRepository, final PostDTOMapper postDTOMapper) {
		this.postRepository = postRepository;
                this.postDTOMapper = postDTOMapper;
	}

	@Override
	public List<PostDTO> getPosts() {
		List<Post> posts = postRepository.findAll();
                List<PostDTO> postDTOs = new ArrayList<>();

                for(Post post : posts)
                        postDTOs.add(postDTOMapper.apply(post));

                return postDTOs;
	}

	@Override
	public PostDTO getPost(Long postId) {
		Optional<Post> optionalPost = postRepository.findById(postId);
                if (optionalPost.isPresent())
                        return postDTOMapper.apply(optionalPost.orElse(null));
                else
                        throw new NoSuchElementException("Invalid post ID provided");
	}

	@Override
	public PostDTO updatePost(Long postId, PostDTO postDTO) {
		Optional<Post> optionalPost = postRepository.findById(postId);
                if (optionalPost.isPresent()) {
                        Post originalPost = optionalPost.get();

                        if (postDTO.title() != null)
                                originalPost.setTitle(postDTO.title());

                        if (postDTO.description() != null)
                                originalPost.setDescription(postDTO.description());

                        if (isValidPost(originalPost)) {
                                Post post = postRepository.save(originalPost);
                                return postDTOMapper.apply(post);
                        }
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
