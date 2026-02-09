package com.sapna.Spendwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapna.Spendwise.entity.Category;
import com.sapna.Spendwise.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category c1){
		Category categoryAdded = service.create(c1);
		return new ResponseEntity<>(categoryAdded,HttpStatus.CREATED);
	}
	@GetMapping("/fetchAllCategories")
	public ResponseEntity<List<Category>> fetchAllCategories(){
		
		List<Category> AllCategories = service.fetchAll();
		return new ResponseEntity<>(AllCategories,HttpStatus.OK);
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity<Category> fetchById(@PathVariable Long id){
		
		Category byId = service.getById(id);
		return new ResponseEntity<>(byId,HttpStatus.OK);
	}
	
	@PutMapping("/byId/{id}")
	public ResponseEntity<Category> updateCategoryById(@RequestBody Category c1,@PathVariable Long id){
		
		Category updateById = service.updateById(c1,id);
		return new ResponseEntity<>(updateById,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		service.deleteByid(id);
		return ResponseEntity.ok("Category Deleted Successfully with id " + id);
	}
	
	@PutMapping("/restore/{id}")
	public ResponseEntity<Category> restore(@PathVariable Long id){
		Category category = service.restoreById(id);
		return new ResponseEntity<>(category,HttpStatus.OK);
	}

}
