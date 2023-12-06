package com.artport.artport.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artport.artport.entities.Post;
import com.artport.artport.entities.User;
import com.artport.artport.repositories.PostRepository;
import com.artport.artport.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
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
	public User getUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent())
			return optionalUser.get();
        else
        	throw new NoSuchElementException("Invalid user ID provided");
	}
	
	@Override
	public User createUser(User user) {
		if (isValidUser(user)) {
			user.setHierarchy((short) 0);
			return userRepository.save(user);
		}
		else
			throw new IllegalArgumentException("Invalid user data");
	}

	@Override
	public User updateUser(Long userId, User user) {
		Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User originalUser = optionalUser.get();
            
            if (user.getUsername() != null)
            	originalUser.setUsername(user.getUsername());
            
            if (user.getEmail() != null)
            	originalUser.setEmail(user.getEmail());
            
            if (user.getPassword() != null)
            	originalUser.setPassword(user.getPassword());
            
            if (user.getHierarchy() != null)
            	originalUser.setHierarchy(user.getHierarchy());
            
            if (isValidUser(originalUser))
                return userRepository.save(originalUser);
            else
                throw new IllegalArgumentException("Invalid post data");
        }
        else
        	throw new NoSuchElementException("User not found");
	}

	@Override
	public void deleteUser(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
	    if (userOptional.isPresent())
	        userRepository.delete(userOptional.get());
	    else
	        throw new NoSuchElementException("User not found");
	}

	@Override
	public List<Post> getPostsByUserId(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent())
			return postRepository.findByUserId(userId);
        else
        	throw new NoSuchElementException("Invalid user ID provided");
	}

	@Override
	public Post createPost(Long userId, Post post) {
		Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User originalUser = optionalUser.get();
            post.setUser(originalUser);

            if (isValidPost(post))
            	return postRepository.save(post);
            else
            	throw new IllegalArgumentException("Invalid post data");
        }
        else
        	throw new NoSuchElementException("Invalid user ID provided");
	}
	
	@Override
	@Transactional
	public void deletePosts(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
        	//List<Post> posts = postRepository.findByUserId(userId);
        	//for(Post post : posts)
        	//	postRepository.delete(post);
        	postRepository.deleteByUserId(userId);
        }
        else
        	throw new NoSuchElementException("Invalid user ID provided");
	}
	
	private boolean isValidPost(Post post) {
		if (post == null || post.getTitle() == null || post.getDescription() == null || post.getUser() == null)
	        return false;
		else if (post.getTitle().isEmpty() || post.getDescription().isEmpty())
	        return false;
		else if (post.getTitle().length() > 50 || post.getDescription().length() > 2000)
	    	return false;

	    return true;
	}

	private boolean isValidUser(User user) {
		if (user == null || user.getUsername() == null || user.getEmail() == null || user.getPassword() == null)
	        return false;
		else if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty())
	        return false;
		else if (user.getUsername().length() > 24 || user.getEmail().length() > 50 || user.getPassword().length() > 50)
	    	return false;

	    return true;
	}
}
