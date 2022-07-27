package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dto.OperationDTO;
import com.entities.Operation;
import com.entities.User;
import com.entities.enums.OperationType;
import com.exceptions.CustomError;
import com.repositories.OperationRepository;
import com.repositories.UserRepository;

@Service
public class OperationService {
	@Autowired
	private OperationRepository repositoryOp;

	@Autowired
	private UserRepository repositoryUser;

	public List<Operation> findAll() {
		return repositoryOp.findAll();
	}

	public Operation[] createOperation(Long user_id, OperationDTO op) {
		Optional<User> user = repositoryUser.findById(user_id);
		if (!user.isPresent()) {
			throw new CustomError("User not found", HttpStatus.NOT_FOUND);
		}
		User recipient = repositoryUser.findByAccountNumber(op.getRecipientAccountNumber());
		if (recipient == null || recipient.getId() == user.get().getId()) {
			throw new CustomError("User not found", HttpStatus.NOT_FOUND);
		}
		OperationType type = op.getType();
		Operation[] operations;
		switch (type) {
			case RECEIVE:
				operations = this.receive(user.get(), op, recipient);
				break;
			case TRANSFER:
				operations = this.trasfer(user.get(), op, recipient);
				break;
			default:
				throw new CustomError("Operation not exist", HttpStatus.NOT_FOUND);
		}
		return operations;
	}

	public Operation[] receive(User user, OperationDTO op, User recipient) {
		user.setBalance(user.getBalance() + op.getValue());
		recipient.setBalance(recipient.getBalance() - op.getValue());
		repositoryUser.save(recipient);
		repositoryUser.save(user);
		Operation op1 = new Operation(op.getType(), op.getValue(), op.getDate(), op.getDescription(), recipient.getId(),
				user);
		Operation op2 = new Operation(OperationType.TRANSFER, op.getValue(), op.getDate(), op.getDescription(), user.id,
				recipient);
		Operation newOp1 = repositoryOp.save(op1);
		Operation newOp2 = repositoryOp.save(op2);
		Operation[] operations = { newOp1, newOp2 };
		return operations;
	}

	public Operation[] trasfer(User user, OperationDTO op, User recipient) {
		Double balanceUser = user.getBalance();
		if (balanceUser >= op.getValue()) {
			user.setBalance(balanceUser - op.getValue());
			recipient.setBalance(recipient.getBalance() + op.getValue());
			repositoryUser.save(recipient);
			repositoryUser.save(user);
			Operation op1 = new Operation(op.getType(), op.getValue(), op.getDate(), op.getDescription(),
					recipient.getId(), user);
			Operation op2 = new Operation(OperationType.RECEIVE, op.getValue(), op.getDate(), op.getDescription(),
					user.id, recipient);
			Operation newOp1 = repositoryOp.save(op1);
			Operation newOp2 = repositoryOp.save(op2);
			Operation[] operations = { newOp1, newOp2 };
			return operations;
		}
		throw new CustomError("Balance is not available", HttpStatus.NOT_FOUND);
	}

}
