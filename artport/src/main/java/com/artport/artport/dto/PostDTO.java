package com.artport.artport.dto;

public record PostDTO (
	Long postId,
	UserDTO user,
	String title,
	String description
){}
