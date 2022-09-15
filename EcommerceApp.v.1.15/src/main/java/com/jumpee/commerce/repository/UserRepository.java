package com.jumpee.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpee.commerce.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	User findById(int id);
	User findByEmail(String email);
}
