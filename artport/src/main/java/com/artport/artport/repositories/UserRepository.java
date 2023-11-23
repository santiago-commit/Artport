package com.artport.artport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.artport.artport.domain.entities.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

}
