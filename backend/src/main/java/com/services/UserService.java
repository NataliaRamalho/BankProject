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
		User user = new User(u.name, u.password, u.email);
		User userFound = repository.findByEmail(u.email);
		if (userFound != null) {
			throw new CustomError("User already register", HttpStatus.NOT_ACCEPTABLE);
		}
		User newUser = repository.save(user);
		return newUser;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User searchUser(String email, String password) {
		String newPassword = User.encryptedPassword(password);
		return repository.findByEmailAndPassword(email, newPassword);
	}

}
