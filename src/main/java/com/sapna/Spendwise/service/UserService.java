package com.sapna.Spendwise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapna.Spendwise.entity.Users;
import com.sapna.Spendwise.exception.UserAlreadyActiveException;
import com.sapna.Spendwise.exception.UserDeletedAlreadyException;
import com.sapna.Spendwise.exception.UserNotFoundException;
import com.sapna.Spendwise.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	//create
	public Users createUser(Users u1){
		
		if(u1.getName()==null||u1.getName().trim().isEmpty()) {
			
			throw new IllegalArgumentException("User name cannot be null value");
		}
		if(u1.getEmail()==null||u1.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("User email cannot be null value");
		}
		Users saveData = userRepo.save(u1);
		
		return saveData;
	}

//fetch
	public List<Users> fetchAll() {
		
		List<Users> allUsers = userRepo.findByIsDeletedFalse();
		return allUsers;
	}

//update
	public Users updateUser(Users u1, int userId) {
	
		Users user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User with not found" + userId));
		 if(user.isDeleted()==true) {
			  throw new UserDeletedAlreadyException("Deleted User cannot be updated.");
		  }
		user.setName(u1.getName());
		user.setEmail(u1.getEmail());
		Users updatedUser = userRepo.save(user);
		return updatedUser;
		
	}

//fetchById
	public Users getUserByid(int id) {
		
		Users u1 = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("User not found with id" + id));
		 if(u1.isDeleted()==true) {
			  throw new UserDeletedAlreadyException("User is deleted already with id : "+id);
		  }
		return u1;
	}

//deleteById
	public void deleteUserById(int id) {
		
	Users user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("User with not found" + id));
	
	  if(user.isDeleted()==true) {
		  throw new UserDeletedAlreadyException("User is deleted already with id"+ id);
	  }
	  user.setDeleted(true);
	  userRepo.save(user);
		
	}

	public Users restoreById(int id) {
		
		
		Users user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("User with specific id is not found" + id));
		
		if(user.isDeleted()==false) {
			
			throw new UserAlreadyActiveException("User is already active cannot restore it");
		}
		
		user.setDeleted(false);
		return userRepo.save(user);
	}
	
	

}
