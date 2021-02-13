package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.entities.User;
import com.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Ana", "000", "5555", "555", 0.0);
		
		userRepository.save(u1);

		
	} 

}
