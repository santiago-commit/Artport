package com.artport.artport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.artport.artport.domain.entities.Post;

@Component
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);
	
	Post findByUserId(Long userId, Long postId);
	
	void deleteAllByUserId(Long userId);
}
