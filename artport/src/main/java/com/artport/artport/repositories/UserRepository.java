package com.artport.artport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.artport.artport.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
