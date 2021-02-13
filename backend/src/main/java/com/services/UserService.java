package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.User;
import com.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User createUser(User u) {
		return repository.save(u);
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	
	public User seachUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	

}
