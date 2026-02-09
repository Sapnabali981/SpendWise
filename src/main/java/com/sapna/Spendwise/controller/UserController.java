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

import com.sapna.Spendwise.entity.Users;
import com.sapna.Spendwise.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	
	@PostMapping("/addUser")
	public ResponseEntity<String> onCreate(@RequestBody Users u1) {
		service.createUser(u1);
		return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/fetchAllUser")
	public ResponseEntity<List<Users>> fetchUserDetails() {
		List<Users> fetchAllUser = service.fetchAll();
		return new ResponseEntity<>(fetchAllUser, HttpStatus.OK);
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable int id){
		
		Users user = service.getUserByid(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<Users> UpdateEmployee(@RequestBody Users u1,@PathVariable int userId){
		
		Users updateUser = service.updateUser(u1,userId);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable int id){
		
		service.deleteUserById(id);
		return new ResponseEntity<>("User is deleted successfully",HttpStatus.OK);
	}
	
	@PutMapping("/restore/{id}")
	public ResponseEntity<Users> restoreUserById(@PathVariable int id){
		Users user = service.restoreById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
}