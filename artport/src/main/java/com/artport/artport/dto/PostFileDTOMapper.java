package com.artport.artport.dto;

import com.artport.artport.entities.PostFile;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class PostFileDTOMapper implements Function<PostFile, PostFileDTO> {

    @Override
    public PostFileDTO apply(PostFile postFile) {
        PostDTO postDTO = new PostDTOMapper().apply(postFile.getPost());
        return new PostFileDTO(postFile.getUuid(), postFile.getDescription(), postDTO);
    }
    
}
