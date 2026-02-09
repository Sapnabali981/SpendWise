package com.sapna.Spendwise.exception;

public class ExpenseNotFoundException extends RuntimeException {

	public ExpenseNotFoundException(String message) {
		super(message);
	}
}
