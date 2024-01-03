package com.artport.artport.services;

import com.artport.artport.dto.PostDTO;
import com.artport.artport.dto.PostDTOMapper;
import com.artport.artport.dto.UserDTO;
import com.artport.artport.dto.UserDTOMapper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artport.artport.entities.Post;
import com.artport.artport.entities.User;
import com.artport.artport.repositories.PostRepository;
import com.artport.artport.repositories.UserRepository;

import jakarta.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
        private final UserDTOMapper userDTOMapper;
        private final PostDTOMapper postDTOMapper;
	
	public UserServiceImpl(final UserRepository userRepository, final PostRepository postRepository, final UserDTOMapper userDTOMapper, final PostDTOMapper postDTOMapper) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
                this.userDTOMapper = userDTOMapper;
                this.postDTOMapper = postDTOMapper;
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userRepository.findAll();
                List<UserDTO> userDTOs = new ArrayList<>();
                
                for (User user: users)
                    userDTOs.add(userDTOMapper.apply(user));

                return userDTOs;
        }

	@Override
	public UserDTO getUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
                        return userDTOMapper.apply(user);
                }
        else
        	throw new NoSuchElementException("Invalid user ID provided");
	}
	
	@Override
	public UserDTO createUser(User user) {
		if (isValidUser(user)) {
			user.setHierarchy((short) 0);
			User savedUser = userRepository.save(user);
                        return userDTOMapper.apply(savedUser);
		}
		else
			throw new IllegalArgumentException("Invalid user data");
	}

	@Override
	public UserDTO updateUser(Long userId, UserDTO userDTO) {
		Optional<User> optionalUser = userRepository.findById(userId);
                if (optionalUser.isPresent()) {
                        User originalUser = optionalUser.get();

                        if (userDTO.username() != null)
                                originalUser.setUsername(userDTO.username());

                        if (userDTO.email() != null)
                                originalUser.setEmail(userDTO.email());

                        if (userDTO.hierarchy() != null)
                                originalUser.setHierarchy(userDTO.hierarchy());

                        if (isValidUser(originalUser)) {
                                User user = userRepository.save(originalUser);
                                return userDTOMapper.apply(user);
                        }
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
	public List<PostDTO> getPostsByUserId(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			List<Post> posts = postRepository.findByUserUserId(userId);
                        List<PostDTO> postDTOs = new ArrayList<>();
                        
                        for (Post post: posts) {
                            postDTOs.add(postDTOMapper.apply(post));
                        }
                        
                        return postDTOs;
                }
        else
        	throw new NoSuchElementException("Invalid user ID provided");
	}

	@Override
	public PostDTO createPost(Long userId, Post post) {
		Optional<User> optionalUser = userRepository.findById(userId);
                if (optionalUser.isPresent()) {
                        User originalUser = optionalUser.get();
                        post.setUser(originalUser);

                        if (isValidPost(post)) {
                                Post savedPost = postRepository.save(post);
                                return postDTOMapper.apply(savedPost);
                        }
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
                if (optionalUser.isPresent())
                        postRepository.deleteByUserId(userId);
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
