package com.jumpee.commerce.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.exception.NullHandlerException;
import com.jumpee.commerce.model.Transaction;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.model.Wallet;
import com.jumpee.commerce.repository.TransactionRepository;
import com.jumpee.commerce.repository.WalletRepository;
import com.jumpee.commerce.utils.DateAndTime;

@Service
public class WalletService 
{
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Wallet findById(int id)
	{
		if (walletRepository.findById(id) != null) 
		{
			throw new NullHandlerException();
		}
		return walletRepository.findById(id);	
	}
	
	public Wallet findByUser(User user)
	{
		if (walletRepository.findByUser(user) != null) 
		{
			throw new NullHandlerException();
		}
		return walletRepository.findByUser(user);
	}

	public String deposit(User userId, Wallet wallet) 
	{
		Transaction record = new Transaction();
		DateAndTime timestamp = new DateAndTime();
		Wallet newWallet = new Wallet();
		if (walletRepository.findByUser(userId) == null) 
		{
			newWallet.setAmount(wallet.getAmount());
			newWallet.setUser(userId);
			walletRepository.save(newWallet);
			
			record.setCategory("Wallet");
			record.setActivity("Deposit an amount of "+wallet.getAmount());
			record.setStatus("Success");
			record.setAtDateAndTime(timestamp.getTimestamp());
			record.setUser(userId);
			transactionRepository.save(record);
			return "Successfully deposited";
		}
		
		Wallet balUpdate = walletRepository.findByUser(userId);
		BigDecimal dbBal = balUpdate.getAmount();
		BigDecimal userBal = wallet.getAmount();
		BigDecimal newBal = dbBal.add(userBal);
		balUpdate.setAmount(newBal);
		
		record.setCategory("Wallet");
		record.setActivity("Deposit an amount of "+userBal);
		record.setStatus("Success");
		record.setAtDateAndTime(timestamp.getTimestamp());
		record.setUser(userId);
		
		transactionRepository.save(record);
		walletRepository.save(balUpdate);
		return "Balance successfully updated";
		
		
		
	}
}
