package com.jumpee.commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.exception.TransactionNotFoundException;
import com.jumpee.commerce.model.Transaction;
import com.jumpee.commerce.repository.TransactionRepository;

@Service
public class TransactionService 
{
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getWalletHistory() 
	{
		List<Transaction> transactions = transactionRepository.findByCategoryContainingIgnoreCase("Wallet");
		boolean transacExist = transactions.isEmpty();
		if(!transacExist)
		{
	        return transactions;
		}
		throw new TransactionNotFoundException();
	}
	public List<Transaction> getCartHistory() 
	{
		List<Transaction> transactions = transactionRepository.findByCategoryContainingIgnoreCase("Cart");
		boolean transacExist = transactions.isEmpty();
		if(!transacExist)
		{
	        return transactions;
		}
		throw new TransactionNotFoundException();
	}
	public List<Transaction> getOrderLogs() 
	{
		List<Transaction> transactions = transactionRepository.findByCategoryContainingIgnoreCase("Order");
		boolean transacExist = transactions.isEmpty();
		if(!transacExist)
		{
	        return transactions;
		}
		throw new TransactionNotFoundException();
	}
	public List<Transaction> getAccountLogs() 
	{
		List<Transaction> transactions = transactionRepository.findByCategoryContainingIgnoreCase("Account");
		boolean transacExist = transactions.isEmpty();
		if(!transacExist)
		{
	        return transactions;
		}
		throw new TransactionNotFoundException();
	}
	
	
}
