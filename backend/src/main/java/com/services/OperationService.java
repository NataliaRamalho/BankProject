package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Operation;
import com.entities.User;
import com.entities.enums.OperationType;
import com.repositories.OperationRepository;
import com.repositories.UserRepository;

@Service
public class OperationService {
	@Autowired
	private OperationRepository repositoryOp;
	
	@Autowired
	private UserRepository repositoryUser;
	
	
	public List<Operation> findAll(){
		return repositoryOp.findAll();
	}

	public Operation createOperation(Long user_id, Operation op) {

		Optional<User> user = repositoryUser.findById(user_id);
		if (!user.isPresent()) {
			return null;
		}
		OperationType type = op.getType();
		switch (type) {
		case DEPOSIT:
			this.deposit(user.get(), op.getValue());
			break;
		case WITHDRAW:
			this.widthdraw(user.get(), op.getValue());
			break;
		case TRANSFER:
			this.trasfer(user.get(), op.getValue(), op.getRecipientId());
		break;
		default:
			return null;
		}

		op.setClient(user.get());
		return repositoryOp.save(op);
	}
	
	public User deposit(User user, Double value) {
		Double newBalance = user.getBalance() + value;
		user.setBalance(newBalance);
		return repositoryUser.save(user);
	}
	
	public User widthdraw(User user, Double value) {
		Double balanceUser = user.getBalance();
		if(balanceUser >= value) {
			user.setBalance(balanceUser - value);
			return repositoryUser.save(user);
		}
		return null;
	}
	
	public User trasfer(User user, Double value, Long recipientId) {
		Double balanceUser = user.getBalance();
		if(balanceUser >= value) {
			if(recipientId != null) {
			   Optional<User> recipient  = repositoryUser.findById(recipientId);
			   if (recipient.isPresent() && recipientId != user.getId()) {
				   user.setBalance(balanceUser - value);
					return repositoryUser.save(user);
				}
			}
			
		}
		return null;
	}
	
}
