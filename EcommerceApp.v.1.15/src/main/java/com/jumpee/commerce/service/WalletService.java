package com.jumpee.commerce.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.exception.NullHandlerException;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.model.Wallet;
import com.jumpee.commerce.repository.WalletRepository;

@Service
public class WalletService 
{
	@Autowired
	private WalletRepository walletRepository;
	
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
		Wallet newWallet = new Wallet();
		System.out.println("TOKEN =" +newWallet);
		System.out.print("TOKEN =" +wallet);
		if (walletRepository.findByUser(userId) == null) 
		{
			newWallet.setBalance(wallet.getBalance());
			newWallet.setUser(userId);
			walletRepository.save(newWallet);
			return "Successfully deposited";
		}
		
		Wallet balUpdate = walletRepository.findByUser(userId);
		BigDecimal dbBal = balUpdate.getBalance();
		BigDecimal userBal = wallet.getBalance();
		BigDecimal newBal = dbBal.add(userBal);
		balUpdate.setBalance(newBal);
		walletRepository.save(balUpdate);
		return "Balance successfully updated";
		
	}
//	public String checkBal(User userId, Wallet wallet) 
//	{
//		Wallet newWallet = new Wallet();
//		if (walletRepository.findByUser(userId) == null) 
//		{
//			newWallet.setBalance(wallet.getBalance());
//			newWallet.setUser(userId);
//			walletRepository.save(newWallet);
//			return "Successfully deposited";
//		}
//		Wallet balUpdate = walletRepository.findByUser(userId);
//		balUpdate.setBalance(wallet.getBalance());
//		return "Balance successfully updated";
//		
//	}
}
