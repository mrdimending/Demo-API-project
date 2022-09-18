package com.jumpee.commerce.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.exception.AuthCheckerHandler;
import com.jumpee.commerce.exception.ItemNotFoundException;
import com.jumpee.commerce.exception.TokenHandlerException;
import com.jumpee.commerce.model.Auth;
import com.jumpee.commerce.model.Transaction;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.repository.AuthRepository;
import com.jumpee.commerce.repository.SingleProductRepository;
import com.jumpee.commerce.repository.TransactionRepository;
import com.jumpee.commerce.repository.UserRepository;
import com.jumpee.commerce.utils.DateAndTime;

@Service
public class AuthService 
{
	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	Transaction record = new Transaction();
	DateAndTime timestamp = new DateAndTime();
	
	public Auth findById(int id) 
	{
		if (authRepository.findById(id) == null )
		{
			throw new AccessDeniedException();
		}
		return authRepository.findById(id);
	}
	public Auth findByToken(Auth token)
	{
		if (authRepository.findByToken(token) == null )
		{
			throw new AccessDeniedException();
		}
		return authRepository.findByToken(token);
	}
	
	public Auth createToken(@Valid User newUser, String verifyCode)
	{
		Auth token = new Auth();
		token.setToken(verifyCode);
		token.setUser(newUser);
		return authRepository.save(token);
		
	}
	public User verifyToken(@Valid Auth token) 
	{
		
		
		if (authRepository.findByToken(token.getToken()) == null) 
		{
			throw new TokenHandlerException();
		}
		Auth tokenExists = authRepository.findByToken(token.getToken());
		tokenExists.setToken(null);
		authRepository.save(tokenExists);
		return tokenExists.getUser();
		
	}
	
	public Auth updateAuth(@Valid User checkUser, String code) 
	{
		User accountExist = userRepository.findByEmail(checkUser.getEmail());
		
		if(authRepository.findByUser(accountExist) == null)
		{
			throw new AuthCheckerHandler();
		}
		Auth tokenExists = authRepository.findByUser(accountExist);
		tokenExists.setAuthz(code);
		tokenExists.setUser(accountExist);
		
		record.setCategory("Account");
		record.setActivity("Logged in");
		record.setStatus("Success");
		record.setAtDateAndTime(timestamp.getTimestamp());
		record.setUser(tokenExists.getUser());
		
		transactionRepository.save(record);
		return authRepository.save(tokenExists);
		
	}
	public boolean findAuthz(String token) 
	{
		if(authRepository.findByAuthz(token) == null)
		{
			return false;
		}
		return true;
	}
	public User findUserByToken(String token) 
	{
		if(authRepository.findByAuthz(token) == null)
		{
			throw new AccessDeniedException();
		}
		Auth findUser = authRepository.findByAuthz(token);
		return findUser.getUser();
	}
	public Auth checkRelation(User getUserDetails, String token) 
	{
		Auth checkAuth = authRepository.findByUser(getUserDetails);
		
		String dbToken = checkAuth.getAuthz();
		String userInputToken = token;
		if(dbToken.equals(userInputToken))
		{
			return checkAuth;	
		}
		throw new AuthCheckerHandler();
	}
	public String accountLogout(User userId) 
	{
		if (authRepository.findByUser(userId) == null) 
		{
			throw new AccessDeniedException();
		}
		
		Auth tokenExists = authRepository.findByUser(userId);
		tokenExists.setAuthz(null);
		
		record.setCategory("Account");
		record.setActivity("Logged out");
		record.setStatus("Success");
		record.setAtDateAndTime(timestamp.getTimestamp());
		record.setUser(tokenExists.getUser());
		
		transactionRepository.save(record);
		authRepository.save(tokenExists);
		return "Successfully Logged out..";
	}
}
