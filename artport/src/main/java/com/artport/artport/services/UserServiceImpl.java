package com.artport.artport.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.artport.artport.domain.entities.Post;
import com.artport.artport.domain.entities.User;
import com.artport.artport.repositories.PostRepository;
import com.artport.artport.repositories.UserRepository;

@Component
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserServiceImpl(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User originalUser = optionalUser.get();
            // Update the necessary properties of the user object
            originalUser.setUsername(user.getUsername());
            originalUser.setEmail(user.getEmail());
            originalUser.setPassword(user.getPassword());
            originalUser.setHierarchy(user.getHierarchy());
            // Save the updated user object
            // User savedUser = userRepository.save(user);
            // return ResponseEntity.ok(savedUser);
        } else {
            // return ResponseEntity.notFound().build();
        }
        
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<Post> getPostsByUserId(Long id) {
		return postRepository.findByUserId(id);
	}

	@Override
	public Post getPostByUserId(Long id, Long postId) {
		return postRepository.findByUserId(id, postId);
	}

	@Override
	public Post createPost(Long id, Post post) {
		Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User originalUser = optionalUser.get();
            // Update the necessary properties of the post object
            post.setUser(originalUser);
            // Save the updated post object
            // User savedPost = userRepository.save(post);
            // return ResponseEntity.ok(savedPost);
        } else {
            // return ResponseEntity.notFound().build();
        }
        
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Long id, Long postId, Post post) {
        Post originalPost = postRepository.getReferenceById(id);
        originalPost.setTitle(post.getTitle());
        originalPost.setDescription(post.getDescription());
		return postRepository.save(originalPost);
	}

	@Override
	public void deletePosts(Long id) {
		postRepository.deleteAllByUserId(id);
	}

}
