package com.jumpee.commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.model.Transaction;
import com.jumpee.commerce.service.AuthService;
import com.jumpee.commerce.service.TransactionService;
import com.jumpee.commerce.view.View;

@RestController
public class TransactionController 
{
	@Autowired
	private AuthService authService;
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/transaction/wallet")
	@JsonView(View.Base.class)
    public ResponseEntity<List<Transaction>> walletHistory(@RequestParam(defaultValue = "guest") String token)
    {
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		List<Transaction> list = transactionService.getWalletHistory();
		
		return new ResponseEntity<List<Transaction>>(list, new HttpHeaders(), HttpStatus.FOUND); 
    }
	
	@GetMapping("/transaction/cart")
	@JsonView(View.Base.class)
    public ResponseEntity<List<Transaction>> cartHistory(@RequestParam(defaultValue = "guest") String token)
    {
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		List<Transaction> list = transactionService.getCartHistory();
		
		return new ResponseEntity<List<Transaction>>(list, new HttpHeaders(), HttpStatus.FOUND); 
    }
	
	@GetMapping("/transaction/order")
	@JsonView(View.Base.class)
    public ResponseEntity<List<Transaction>> orderHistory(@RequestParam(defaultValue = "guest") String token)
    {
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		List<Transaction> list = transactionService.getOrderLogs();
		
		return new ResponseEntity<List<Transaction>>(list, new HttpHeaders(), HttpStatus.FOUND); 
    }
	
	@GetMapping("/transaction/account")
	@JsonView(View.Base.class)
    public ResponseEntity<List<Transaction>> accountLogs(@RequestParam(defaultValue = "guest") String token)
    {
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		List<Transaction> list = transactionService.getAccountLogs();
		
		return new ResponseEntity<List<Transaction>>(list, new HttpHeaders(), HttpStatus.FOUND); 
    }
}
