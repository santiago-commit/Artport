package com.artport.artport.domain.dto;

public class UserDTO {

	Long userId;
	String username;
	String email;
	Short hierarchy;
	
	public UserDTO(Long userId, String username, String email, Short hierarchy) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.hierarchy = hierarchy;
	}
	
	public UserDTO() {}

	public Long getId() {
		return userId;
	}

	public void setId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Short hierarchy) {
		this.hierarchy = hierarchy;
	}
}
