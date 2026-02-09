package com.sapna.Spendwise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapna.Spendwise.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

	List<Category> findByIsDeletedFalse();

}
