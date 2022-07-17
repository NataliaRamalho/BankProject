package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.entities.User;
import com.exceptions.CustomError;
import com.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User createUser(User u) {
		User user = repository.findByEmail(u.email);
		if (user != null) {
			throw new CustomError("User already register", HttpStatus.NOT_ACCEPTABLE);
		}
		User newUser = repository.save(u);
		return newUser;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User seachUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

}
