package com.sapna.Spendwise.exception;

import com.sapna.Spendwise.entity.Expense;

public class ExpenseNotFoundWithUserIdException extends RuntimeException {
	public ExpenseNotFoundWithUserIdException(String message) {
		super(message);
	}

}
