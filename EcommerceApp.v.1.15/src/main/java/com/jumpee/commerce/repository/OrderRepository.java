package com.jumpee.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpee.commerce.model.Order;
import com.jumpee.commerce.model.User;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
	Order findByUser(User user);
}
