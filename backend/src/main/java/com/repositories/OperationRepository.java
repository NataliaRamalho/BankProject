package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{

}
