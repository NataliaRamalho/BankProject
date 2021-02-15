package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Operation;
import com.repositories.OperationRepository;

@Service
public class OperationService {
	@Autowired
	private OperationRepository repository;
	
	public List<Operation> findAll(){
		return repository.findAll();
	}
	
	public Operation createOperation(Operation op) {
		return repository.save(op);
	}
	
	
}
