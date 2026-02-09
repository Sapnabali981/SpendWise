package com.sapna.Spendwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapna.Spendwise.entity.Category;
import com.sapna.Spendwise.exception.CategoryAlreadyActiveException;
import com.sapna.Spendwise.exception.CategoryAlreadyDeletedException;
import com.sapna.Spendwise.exception.CategoryNotFoundException;
import com.sapna.Spendwise.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category create(Category c1) {

		if (c1.getName() == null || c1.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Category name cannot be empty");
		}
		return repo.save(c1);

	}

	public List<Category> fetchAll() {

		return repo.findByIsDeletedFalse();
	}

	public Category getById(Long id) {

		Category category = repo.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id:" + id));
		if (category.isDeleted() == true) {
			throw new CategoryAlreadyDeletedException("Category is already deleted with id: " + id);
		}
		return category;
	}

	public Category updateById(Category c1, Long id) {

		Category category = repo.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id:" + id));
		if (category.isDeleted() == true) {
			throw new CategoryAlreadyDeletedException("Deleted category cannot be updated");
		}
		category.setName(c1.getName());
		return repo.save(category);
	}

	public void deleteByid(Long id) {
		Category category = repo.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id:" + id));
	
		if (category.isDeleted() == true) {
			throw new CategoryAlreadyDeletedException("Category is already deleted");
		}
		category.setDeleted(true);
		repo.save(category);

	}

	public Category restoreById(Long id) {

		Category category = repo.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id:" + id));
		if (category.isDeleted()==false) {
			throw new CategoryAlreadyActiveException("Category is active cannot restore it again");
		}
		category.setDeleted(false);
		return repo.save(category);
	}

}
