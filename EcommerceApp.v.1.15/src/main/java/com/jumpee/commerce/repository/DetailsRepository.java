package com.jumpee.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpee.commerce.model.Details;
import com.jumpee.commerce.model.User;

public interface DetailsRepository extends JpaRepository<Details, Integer> 
{
	Details findById(int id);
	Details findByUser(User user);
}
