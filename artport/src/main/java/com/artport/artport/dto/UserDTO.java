package com.artport.artport.dto;

public record UserDTO (
	Long id,
	String username,
	String email,
	Short hierarchy
){
}
