package com.sapna.Spendwise.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sapna.Spendwise.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	
	
List<Expense> findByUserId(int UserId);
List<Expense> findByCategoryId(Long categoryId);

Page<Expense> findByIsDeletedFalse(Pageable pageable);

Page<Expense> findByUserIdAndIsDeletedFalse(int userId,Pageable pageable);

Page<Expense> findByCategoryIdAndIsDeletedFalse(Long categoryId,Pageable pageable);

@Query("SELECT SUM(e.amount) from Expense e where e.user.id=:UserId And e.isDeleted=false")
Double getTotalExpenseByUserId(@Param("UserId") int UserId);

@Query("select sum(e.amount) from Expense e where e.category.id=:categoryId And e.isDeleted=false")
Double getTotalExpenseByCategoryId(@Param("categoryId")Long categoryId);

@Query("select year(e.expenseDate),month(e.expenseDate), sum(e.amount) from Expense e where e.user.id=:userId And e.isDeleted=false group by year(e.expenseDate),month(e.expenseDate)")
List<Object[]> findMonthlyExpenseByUserId(@Param("userId") int userId);
	

@Query("Select sum(e.amount) from Expense e where e.user.id=:userId And e.isDeleted=false And e.expenseDate between :startDate And :endDate")
Double findDateSummaryExpenseByUserId(@Param("userId") int userId,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
}

