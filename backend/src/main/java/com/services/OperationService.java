package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

	public Operation[] createOperation(Long user_id, Operation op) {
		Optional<User> user = repositoryUser.findById(user_id);
		if (!user.isPresent()) {
			throw new CustomError("User not found", HttpStatus.NOT_FOUND);
		}
		OperationType type = op.getType();
		Operation[] operations;
		switch (type) {
			case RECEIVE:
				operations = this.receive(user.get(), op);
				break;
			case TRANSFER:
				operations = this.trasfer(user.get(), op);
				break;
			default:
				throw new CustomError("Operation not exist", HttpStatus.NOT_FOUND);
		}
		return operations;
	}

	public Operation[] receive(User user, Operation op) {
		if (op.getRecipientId() != null) {
			Optional<User> recipient = repositoryUser.findById(op.getRecipientId());
			if (recipient.isPresent() && op.getRecipientId() != user.getId()) {
				user.setBalance(user.getBalance() + op.getValue());
				User recipetData = recipient.get();
				recipetData.setBalance(recipetData.getBalance() - op.getValue());
				repositoryUser.save(recipetData);
				repositoryUser.save(user);
				Operation op1 = new Operation(op.getType(), op.value, op.date, op.description, op.recipientId, user);
				Operation op2 = new Operation(OperationType.TRANSFER, op.value, op.date, op.description, user.id,
						recipetData);
				Operation newOp1 = repositoryOp.save(op1);
				Operation newOp2 = repositoryOp.save(op2);
				Operation[] operations = { newOp1, newOp2 };
				return operations;
			} else {
				throw new CustomError("Recipient not found", HttpStatus.NOT_FOUND);
			}
		}
		throw new CustomError("Recipient not found", HttpStatus.NOT_FOUND);
	}

	public Operation[] trasfer(User user, Operation op) {
		Double balanceUser = user.getBalance();
		if (balanceUser >= op.getValue()) {
			if (op.getRecipientId() != null) {
				Optional<User> recipient = repositoryUser.findById(op.getRecipientId());
				if (recipient.isPresent() && op.getRecipientId() != user.getId()) {
					user.setBalance(balanceUser - op.getValue());
					User recipetData = recipient.get();
					recipetData.setBalance(recipetData.getBalance() + op.getValue());
					repositoryUser.save(recipetData);
					repositoryUser.save(user);
					Operation op1 = new Operation(op.getType(), op.value, op.date, op.description, op.recipientId,
							user);
					Operation op2 = new Operation(OperationType.RECEIVE, op.value, op.date, op.description, user.id,
							recipetData);
					Operation newOp1 = repositoryOp.save(op1);
					Operation newOp2 = repositoryOp.save(op2);
					Operation[] operations = { newOp1, newOp2 };
					return operations;
				} else {
					throw new CustomError("Recipient not found", HttpStatus.NOT_FOUND);
				}
			}

		}
		throw new CustomError("Balance is not available", HttpStatus.NOT_FOUND);

	}

}
