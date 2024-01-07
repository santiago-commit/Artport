package com.artport.artport.dto;

import java.util.UUID;

public record PostFileDTO (
        UUID uuid,
        String description,
        PostDTO post
){}
