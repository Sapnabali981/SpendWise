package com.sapna.Spendwise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sapna.Spendwise.entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
	

	@Query("select u from Users u where u.isDeleted=false")
	List<Users> findByIsDeletedFalse();
}
