package com.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entities.User;
import com.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private UserService services;
	
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users = services.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	
	@PostMapping
	public ResponseEntity<User> registerUser(@RequestBody User u){ 
	    u = services.createUser(u);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(uri).body(u);
	}
	
	
	@PostMapping(value = "login")
	public ResponseEntity<User> login(@RequestBody User u) {
		String email = u.email;
		String password = u.password;
		User user = services.seachUser(email, password);
		if (user == null) {
			return ResponseEntity.status(404).body(user);
		}
		return ResponseEntity.ok().body(user);
	}
	
	
}
