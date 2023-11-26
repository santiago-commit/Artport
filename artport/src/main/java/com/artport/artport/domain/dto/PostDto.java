package com.artport.artport.domain.dto;

import com.artport.artport.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

	private Long postId;
	private User user;
	private String title;
	private String description;
	
	public Long getId() {
		return postId;
	}
	public void setId(Long postId) {
		this.postId = postId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
