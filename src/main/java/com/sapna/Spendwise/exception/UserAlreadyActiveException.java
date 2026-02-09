package com.sapna.Spendwise.exception;

public class UserAlreadyActiveException extends RuntimeException {

	public UserAlreadyActiveException(String message) {
		super(message);
	}
}
