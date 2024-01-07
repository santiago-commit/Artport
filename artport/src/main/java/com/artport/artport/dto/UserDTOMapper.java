package com.artport.artport.dto;

import com.artport.artport.entities.User;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getHierarchy());
    }
    
}
