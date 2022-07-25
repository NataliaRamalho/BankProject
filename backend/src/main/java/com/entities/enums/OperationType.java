package com.entities.enums;

public enum OperationType {
	RECEIVE(0),
	TRANSFER(1);

	private int code;

	private OperationType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OperationType valueOf(int code) {
		for (OperationType value : OperationType.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OperationType");
	}

}
