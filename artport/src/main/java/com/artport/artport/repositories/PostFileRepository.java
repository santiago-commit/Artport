package com.artport.artport.repositories;

import com.artport.artport.entities.PostFile;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFileRepository extends JpaRepository<PostFile, UUID> {
    
}
