package com.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.User;
import com.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User createUser(User u) {
		u.balance = 0.0;
		u.accountNumber = this.generateAccountNumber();
		return repository.save(u);
	}

	private String generateAccountNumber() {
		String accountNumber = "";
		Random random = new Random();
		for (int i = 0; i <= 6; i++) {
			int newNumber = random.nextInt(9);
			accountNumber = accountNumber + newNumber;
		}
		char letter = (char) (random.nextInt(26) + 'a');
		return letter + accountNumber;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User seachUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

}
