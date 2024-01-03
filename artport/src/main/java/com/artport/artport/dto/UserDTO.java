package com.artport.artport.dto;

public record UserDTO (
	Long userId,
	String username,
	String email,
	Short hierarchy
){
}
