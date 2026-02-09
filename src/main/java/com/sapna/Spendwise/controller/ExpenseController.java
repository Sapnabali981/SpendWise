package com.sapna.Spendwise.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapna.Spendwise.dto.DateSummaryExpenseDto;
import com.sapna.Spendwise.dto.ExpenseRequestDto;
import com.sapna.Spendwise.dto.ExpenseSummaryDtoCategory;
import com.sapna.Spendwise.dto.ExpenseSummaryUserDto;
import com.sapna.Spendwise.dto.MonthlyExpenseDto;
import com.sapna.Spendwise.entity.Expense;
import com.sapna.Spendwise.exception.ExpenseNotFoundWithCategoryIdException;
import com.sapna.Spendwise.exception.ExpenseNotFoundWithUserIdException;
import com.sapna.Spendwise.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

	private ExpenseService service;

	public ExpenseController(ExpenseService service) {

		this.service = service;
	}

	@PostMapping("/addExpense")

	public ResponseEntity<Expense> createExpense(@RequestBody ExpenseRequestDto dto) {

		Expense e1 = service.createExpense(dto);
		return new ResponseEntity<>(e1, HttpStatus.CREATED);

	}

	@GetMapping("/getAllExpense")
	public ResponseEntity<Page<Expense>> fetchExpenseDetailsWithPagination(@RequestParam int page,@RequestParam int size, @RequestParam String sortBy, @RequestParam String direction) {

		Page<Expense> expensedata = service.fetchExpenseDetailsWithPagination(page, size, sortBy, direction);
		return new ResponseEntity<>(expensedata, HttpStatus.OK);
	}

	@GetMapping("/fetchById/{id}")
	public ResponseEntity<Expense> fetchExpenseById(@PathVariable Long id) {
		Expense exp = service.fetchById(id);
		return new ResponseEntity<>(exp, HttpStatus.OK);
	}

	@GetMapping("/fetchByUserId")
	public ResponseEntity<Page<Expense>> getExpenseByUserIdWithPagination(@RequestParam int userId,
			@RequestParam int page, @RequestParam int size, @RequestParam String sortBy,
			@RequestParam String direction) {

		Page<Expense> data = service.getExpenseByUserIdWithPagination(userId, page, size, sortBy, direction);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/fetchByCategoryId")
	public ResponseEntity<Page<Expense>> getExpenseByCategoryIdWithPagination(@RequestParam Long categoryId,
			@RequestParam int page, @RequestParam int size, @RequestParam String sortBy,
			@RequestParam String direction) {

		Page<Expense> data = service.getExpenseByCategoryIdWithPagination(categoryId, page, size, sortBy, direction);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PutMapping("/updateById/{id}")
	public ResponseEntity<Expense> updateExpenseDetails(@RequestBody ExpenseRequestDto dto, @PathVariable Long id) {
		Expense updateByExpenseId = service.updateByExpenseId(dto, id);
		return new ResponseEntity<>(updateByExpenseId, HttpStatus.OK);
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<>("Expense deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/restore/{id}")
	public ResponseEntity<Expense> restoreExpenseById(@PathVariable Long id) {

		Expense exp = service.restoreById(id);
		return new ResponseEntity<>(exp, HttpStatus.OK);
	}

	@GetMapping("/getTotalExpenseByUserId/{userId}")
	public ResponseEntity<ExpenseSummaryUserDto> getTotalExpenseByUserId(@PathVariable int userId) {

		ExpenseSummaryUserDto e1 = service.getTotalExpenseByUser(userId);

		return new ResponseEntity<>(e1, HttpStatus.OK);
	}

	@GetMapping("/getTotalExpenseByCategoryId/{categoryId}")
	public ResponseEntity<ExpenseSummaryDtoCategory> getExpenseByCategory(@PathVariable Long categoryId) {

		ExpenseSummaryDtoCategory totalExp = service.getTotalExpenseByCategoryId(categoryId);
		return new ResponseEntity<>(totalExp, HttpStatus.OK);
	}

	@GetMapping("/monthly-summary/{userId}")
	public ResponseEntity<List<MonthlyExpenseDto>> getMonthlyExpenseByUser(@PathVariable int userId) {

		List<MonthlyExpenseDto> monthlyExpense = service.getMonthlyExpense(userId);
		return new ResponseEntity<>(monthlyExpense, HttpStatus.OK);
	}

	@GetMapping("/date-summary")
	public ResponseEntity<DateSummaryExpenseDto> getDateSummaryExpense(@RequestParam int userId,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

		DateSummaryExpenseDto dateSummaryExpense = service.getDateSummaryExpense(userId, startDate, endDate);
		return new ResponseEntity<>(dateSummaryExpense, HttpStatus.OK);
	}
}
