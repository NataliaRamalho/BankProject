package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmailAndPassword(String email, String password);
}
