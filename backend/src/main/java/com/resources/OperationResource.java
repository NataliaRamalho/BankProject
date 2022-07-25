package com.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entities.Operation;
import com.exceptions.CustomError;
import com.services.OperationService;

@RestController
@RequestMapping(value = "/operations")
public class OperationResource {

	@Autowired
	private OperationService services;

	@GetMapping
	public ResponseEntity<List<Operation>> findAll() {
		List<Operation> operations = services.findAll();
		return ResponseEntity.ok().body(operations);
	}

	@PostMapping(value = "/{user_id}")
	public ResponseEntity<Object> registerUser(@PathVariable Long user_id, @RequestBody Operation op) {
		try {
			Operation[] ops = services.createOperation(user_id, op);
			if (ops.length > 2) {
				return ResponseEntity.notFound().build();
			}

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ops[0].getId())
					.toUri();
			return ResponseEntity.created(uri).body(op);
		} catch (CustomError e) {
			System.out.println(e);
			return new ResponseEntity<>(e.getMessage(), e.getstatus());
		} catch (Throwable e) {
			System.out.println(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
