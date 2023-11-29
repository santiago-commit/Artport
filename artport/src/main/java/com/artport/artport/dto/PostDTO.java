package com.artport.artport.dto;

public class PostDTO {

	Long postId;
	UserDTO user;
	String title;
	String description;

	public PostDTO(Long postId, UserDTO user, String title, String description) {
		this.postId = postId;
		this.user = user;
		this.title = title;
		this.description = description;
	}
	
	public PostDTO() {}

	public Long getId() {
		return postId;
	}

	public void setId(Long postId) {
		this.postId = postId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUserDTO(UserDTO user) {
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
