package com.jumpee.commerce.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jumpee.commerce.model.Transaction;
import com.jumpee.commerce.model.User;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer>
{
	List<Transaction> findByUser(User user);
	List<Transaction> findByCategoryContainingIgnoreCase(String search);
}
