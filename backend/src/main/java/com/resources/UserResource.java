package com.resources;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entities.User;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "TESTE", "5555", "5555", "555", 555.00);
		return ResponseEntity.ok().body(u);
	}
}
