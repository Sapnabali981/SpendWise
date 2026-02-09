package com.sapna.Spendwise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//this globalExceptionHandler class is responsible to handle the exception for all controllers.
public class GlobalExceptionHandler {
//this class will catcht the exception and handles the exception by sending the meaningful messages to  user in postman 
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleUserInputException(IllegalArgumentException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserDeletedAlreadyException.class)
	public ResponseEntity<String> handleUserDeletedAlreadyException(UserDeletedAlreadyException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserAlreadyActiveException.class)
	public ResponseEntity<String> handleUserDeletedAlreadyException(UserAlreadyActiveException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryAlreadyDeletedException.class)
	public ResponseEntity<String> handleCategoryAlreadyDeletedException(CategoryAlreadyDeletedException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryAlreadyActiveException.class)
	public ResponseEntity<String> handleCategoryAlreadyActiveDeletedException(CategoryAlreadyActiveException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<String> handleExpenseNotFoundException(ExpenseNotFoundException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ExpenseNotFoundWithUserIdException.class)
	public ResponseEntity<String> handleExpenseNotFoundWithUserIdException(ExpenseNotFoundWithUserIdException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExpenseNotFoundWithCategoryIdException.class)
	public ResponseEntity<String> handleExpenseNotFoundWithCategoryIdException(ExpenseNotFoundWithCategoryIdException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ExpenseDeletedAlreadyException.class)
	public ResponseEntity<String> handleExpenseDeletedAlreadyException(ExpenseDeletedAlreadyException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExpenseIsAlreadyActiveException.class)
	public ResponseEntity<String> handleExpenseIsAlreadyActiveException(ExpenseIsAlreadyActiveException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InValidStartDateException.class)
	public ResponseEntity<String> handleInValidStartDateException(InValidStartDateException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}

