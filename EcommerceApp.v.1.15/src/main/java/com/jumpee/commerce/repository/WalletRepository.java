package com.jumpee.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpee.commerce.model.User;
import com.jumpee.commerce.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>
{
	Wallet findById(int id);
	Wallet findByUser(User user);
}
