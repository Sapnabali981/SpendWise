package com.sapna.Spendwise.exception;

public class ExpenseIsAlreadyActiveException extends RuntimeException {

	public ExpenseIsAlreadyActiveException(String message) {
		super(message);
	}
}
