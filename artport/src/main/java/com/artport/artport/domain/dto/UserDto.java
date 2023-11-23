package com.artport.artport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private Long id;
	private String username;
	private String email;
	private String password;
	private Short hierarchy = 0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Short getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(Short hierarchy) {
		this.hierarchy = hierarchy;
	}
}
