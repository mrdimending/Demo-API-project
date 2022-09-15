package com.jumpee.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpee.commerce.model.Auth;
import com.jumpee.commerce.model.User;

public interface AuthRepository extends JpaRepository<Auth, Integer> 
{
	Auth findById(int id);
	Auth findByToken(Auth token);
	Auth findByToken(String token);
	Auth findByUser(User user);
	Auth findByAuthz(Auth newToken);
	Auth findByAuthz(String newToken);
}
