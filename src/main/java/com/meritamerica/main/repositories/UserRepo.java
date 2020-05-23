package com.meritamerica.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meritamerica.main.security.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
	@Query("Select user from Users where user.username = ?1")
	Users findByUserName(String username);


	

}
