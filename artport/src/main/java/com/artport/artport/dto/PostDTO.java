package com.artport.artport.dto;

public record PostDTO (
	Long id,
	UserDTO user,
	String title,
	String description
){}
