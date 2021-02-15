package com.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.entities.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Operation implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public Integer type;
	
	public Double value;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	public Instant date;
	public String recipientId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User client;
	
	public Operation() {
		super();
	}
		
	public Operation(Long id, OperationType type, Double value, Instant date, String recipientId, User client) {
		super();
		this.id = id;
		setType(type);
		this.value = value;
		this.date = date;
		this.recipientId = recipientId;
		this.client = client;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OperationType getType() {
		return OperationType.valueOf(type);
	}

	public void setType(OperationType type) {
		if(type != null) {
			this.type = type.getCode();
		}
		
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	
	
	public User getUser() {
		return client;
	}


	public void setUser(User client) {
		this.client = client;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
