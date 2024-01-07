package com.artport.artport.services;

import com.artport.artport.dto.PostDTO;
import com.artport.artport.dto.UserDTO;
import java.util.List;

import com.artport.artport.entities.Post;
import com.artport.artport.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	
	List<UserDTO> getUsers();
	
	UserDTO getUser(Long userId);

	UserDTO createUser(User user);
	
	UserDTO updateUser(Long userId, UserDTO userDTO);
	
	void deleteUser(Long userId);

	List<PostDTO> getPostsByUserId(Long userId);
	
	PostDTO createPost(Long userId, Post post, List<MultipartFile> files);
	
	void deletePosts(Long userId);
	
}
