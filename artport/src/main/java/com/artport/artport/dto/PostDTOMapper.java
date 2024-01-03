package com.artport.artport.dto;

import com.artport.artport.entities.Post;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class PostDTOMapper implements Function<Post, PostDTO> {

    @Override
    public PostDTO apply(Post post) {
        UserDTO userDTO = new UserDTOMapper().apply(post.getUser());
        return new PostDTO(post.getPostId(), userDTO, post.getTitle(), post.getDescription());
    }
    
}
