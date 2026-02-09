package com.sapna.Spendwise.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sapna.Spendwise.dto.DateSummaryExpenseDto;
import com.sapna.Spendwise.dto.ExpenseRequestDto;
import com.sapna.Spendwise.dto.ExpenseSummaryDtoCategory;
import com.sapna.Spendwise.dto.ExpenseSummaryUserDto;
import com.sapna.Spendwise.dto.MonthlyExpenseDto;
import com.sapna.Spendwise.entity.Category;
import com.sapna.Spendwise.entity.Expense;
import com.sapna.Spendwise.entity.Users;
import com.sapna.Spendwise.exception.CategoryNotFoundException;
import com.sapna.Spendwise.exception.ExpenseDeletedAlreadyException;
import com.sapna.Spendwise.exception.ExpenseIsAlreadyActiveException;
import com.sapna.Spendwise.exception.ExpenseNotFoundException;
import com.sapna.Spendwise.exception.ExpenseNotFoundWithUserIdException;
import com.sapna.Spendwise.exception.InValidStartDateException;
import com.sapna.Spendwise.exception.UserNotFoundException;
import com.sapna.Spendwise.repository.CategoryRepository;
import com.sapna.Spendwise.repository.ExpenseRepository;
import com.sapna.Spendwise.repository.UserRepository;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository repo;

	private UserRepository userRepo;
	private CategoryRepository categoryRepo;

	public ExpenseService(UserRepository userRepo, CategoryRepository categoryRepo) {
		this.userRepo = userRepo;
		this.categoryRepo = categoryRepo;
	}

	public Expense createExpense(ExpenseRequestDto dto) {

		Users user = userRepo.findById(dto.getUser_id())
				.orElseThrow(() -> new UserNotFoundException("User not found with id" + dto.getUser_id()));
		Category category = categoryRepo.findById(dto.getCategory_id())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id" + dto.getCategory_id()));

		Expense e1 = new Expense();
		e1.setAmount(dto.getAmount());
		e1.setExpenseDate(dto.getExpenseDate());
		e1.setDescription(dto.getDescription());

		e1.setUser(user);
		e1.setCategory(category);

		return repo.save(e1);
	}

	public Page<Expense> fetchExpenseDetailsWithPagination(int page,int size,String sortBy,String direction) {

		List<String> allowedSortFields = List.of("amount","expenseDate","createdAt");

		if(!allowedSortFields.contains(sortBy)){
		    throw new IllegalArgumentException("Invalid sort field");
		}
		
		if(page < 0) {
		    throw new IllegalArgumentException("Page number cannot be negative");
		}

		if(size <= 0) {
		    throw new IllegalArgumentException("Size must be greater than 0");
		}
		
		 Sort sort = direction.equalsIgnoreCase("desc")
		            ? Sort.by(sortBy).descending()
		            : Sort.by(sortBy).ascending();
		Pageable pageable=PageRequest.of(page, size,sort);
		Page<Expense> data = repo.findByIsDeletedFalse(pageable);
		return data;
	}

	public Expense fetchById(Long id) {

		Expense exp = repo.findById(id)
				.orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id" + id));
		if (exp.isDeleted() != false) {
			throw new ExpenseDeletedAlreadyException("Deleted expense details cannot be fetched ");
		}
		return exp;
	}

	public Page<Expense> getExpenseByUserIdWithPagination(int userId, int page, int size,String sortBy,String direction) {

		List<String> allowedSortFields = List.of("amount","expenseDate","createdAt");

		if(!allowedSortFields.contains(sortBy)){
		    throw new IllegalArgumentException("Invalid sort field");
		}
		
		boolean existsById = userRepo.existsById(userId);
		if (existsById != true) {
			throw new UserNotFoundException("User Not found with id: " + userId);
		}

		if(page < 0) {
		    throw new IllegalArgumentException("Page number cannot be negative");
		}

		if(size <= 0) {
		    throw new IllegalArgumentException("Size must be greater than 0");
		}
		
		 Sort sort = direction.equalsIgnoreCase("desc")
		            ? Sort.by(sortBy).descending()
		            : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size,sort);

		Page<Expense> data = repo.findByUserIdAndIsDeletedFalse(userId, pageable);
		return data;
	}

	public Page<Expense> getExpenseByCategoryIdWithPagination(Long categoryId, int page, int size,String sortBy,String direction) {

		List<String> allowedSortFields = List.of("amount","expenseDate","createdAt");

		if(!allowedSortFields.contains(sortBy)){
		    throw new IllegalArgumentException("Invalid sort field");
		}
		
		boolean existsById = categoryRepo.existsById(categoryId);
		if (existsById != true) {
			throw new CategoryNotFoundException("Category Not found with id: " + categoryId);
		}
		if(page < 0) {
		    throw new IllegalArgumentException("Page number cannot be negative");
		}

		if(size <= 0) {
		    throw new IllegalArgumentException("Size must be greater than 0");
		}
		
		
		 Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size,sort);
		return repo.findByCategoryIdAndIsDeletedFalse(categoryId, pageable);
	}

	public Expense updateByExpenseId(ExpenseRequestDto dto, Long id) {

		Expense exp = repo.findById(id)
				.orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id" + id));
		Users user = userRepo.findById(dto.getUser_id())
				.orElseThrow(() -> new UserNotFoundException("User Not found with id" + dto.getUser_id()));
		Category category = categoryRepo.findById(dto.getCategory_id())
				.orElseThrow(() -> new CategoryNotFoundException("Category Not found with id" + dto.getCategory_id()));

		if (exp.isDeleted() != false) {
			throw new ExpenseDeletedAlreadyException("Deleted expense cannot be updated");
		}
		exp.setAmount(dto.getAmount());
		exp.setDescription(dto.getDescription());
		exp.setExpenseDate(dto.getExpenseDate());
		exp.setUser(user);
		exp.setCategory(category);
		return repo.save(exp);

	}

	public void deleteById(Long id) {
		Expense exp = repo.findById(id)
				.orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id" + id));
		if (exp.isDeleted() != false) {
			throw new ExpenseDeletedAlreadyException("Expense details already deleted");
		}
		exp.setDeleted(true);
		repo.save(exp);
	}

	public Expense restoreById(Long id) {

		Expense exp = repo.findById(id)
				.orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id" + id));
		if (exp.isDeleted() == false) {
			throw new ExpenseIsAlreadyActiveException("Expense is already active");
		}
		exp.setDeleted(false);
		return repo.save(exp);
	}

	public ExpenseSummaryUserDto getTotalExpenseByUser(int userId) {

		userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id:" + userId));
		Double totalExpense = repo.getTotalExpenseByUserId(userId);
		if (totalExpense == null) {
			totalExpense = 0.0;
		}
		ExpenseSummaryUserDto e1 = new ExpenseSummaryUserDto();
		e1.setTotalExpense(totalExpense);
		e1.setUser_Id(userId);
		return e1;
	}

	public ExpenseSummaryDtoCategory getTotalExpenseByCategoryId(Long categoryId) {

		Category c1 = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
		Double TotalExp = repo.getTotalExpenseByCategoryId(categoryId);
		if (TotalExp == null) {
			TotalExp = 0.0;
		}

		ExpenseSummaryDtoCategory e1 = new ExpenseSummaryDtoCategory();
		e1.setCategory_id(categoryId);
		e1.setCategoryName(c1.getName());
		e1.setTotalExpense(TotalExp);

		return e1;
	}

	public List<MonthlyExpenseDto> getMonthlyExpense(int userId) {
		boolean existsById = userRepo.existsById(userId);
		if (existsById != true) {
			throw new UserNotFoundException("User Not Found With Id" + userId);
		}

		List<Object[]> data = repo.findMonthlyExpenseByUserId(userId);
		if (data.isEmpty()) {
			throw new ExpenseNotFoundWithUserIdException("Expense Not found with UserId" + userId);
		}
		List<MonthlyExpenseDto> a1 = new ArrayList<>();
		for (Object[] row : data) {
			MonthlyExpenseDto d1 = new MonthlyExpenseDto();
			d1.setYear((Integer) row[0]);// well here we are converting the object type data into wrapper class integer
											// type so that we can store it inside the dto class object.
			d1.setMonth((Integer) row[1]);
			d1.setTotalExpense((Double) row[2]);
			a1.add(d1);

		}

		return a1;

	}

	public DateSummaryExpenseDto getDateSummaryExpense(int userId, LocalDate startDate, LocalDate endDate) {
		boolean existsById = userRepo.existsById(userId);
		if (existsById != true) {
			throw new UserNotFoundException("User Not Found With Id" + userId);
		}

		if (startDate.isAfter(endDate)) {
			throw new InValidStartDateException("StartDate cannot come after endDate");
		}

		Double value = repo.findDateSummaryExpenseByUserId(userId, startDate, endDate);
		if (value == null) {
			value = 0.0;// handles null value.
		}
		DateSummaryExpenseDto d1 = new DateSummaryExpenseDto();
		d1.setUserId(userId);
		d1.setTotalExpense(value);
		d1.setStartDate(startDate);
		d1.setEndDate(endDate);
		return d1;
	}

}
