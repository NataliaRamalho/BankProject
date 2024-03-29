package com.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entities.User;
import com.exceptions.CustomError;
import com.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService services;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> users = services.findAll();
		return ResponseEntity.ok().body(users);
	}

	@PostMapping
	public ResponseEntity<Object> registerUser(@RequestBody User u) {
		try {
			u = services.createUser(u);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
			return ResponseEntity.created(uri).body(u);
		} catch (CustomError e) {
			return new ResponseEntity<>(e.getMessage(), e.getstatus());
		} catch (Throwable e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/login")
	public ResponseEntity<User> login(@RequestBody User u) {
		String email = u.email;
		String password = u.password;
		User user = services.searchUser(email, password);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

}
