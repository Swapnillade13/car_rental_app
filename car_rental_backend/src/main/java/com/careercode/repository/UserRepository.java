package com.careercode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careercode.entity.User;
import com.careercode.enums.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	User findByUserRole(UserRole admin);
	
}
