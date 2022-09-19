package com.jumpee.commerce.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.exception.InsufficientBalanceException;
import com.jumpee.commerce.exception.ShippingNotFound;
import com.jumpee.commerce.model.Auth;
import com.jumpee.commerce.model.Cart;
import com.jumpee.commerce.model.Details;
import com.jumpee.commerce.model.Order;
import com.jumpee.commerce.model.Transaction;
import com.jumpee.commerce.model.Wallet;
import com.jumpee.commerce.repository.AuthRepository;
import com.jumpee.commerce.repository.CartRepository;
import com.jumpee.commerce.repository.DetailsRepository;
import com.jumpee.commerce.repository.OrderRepository;
import com.jumpee.commerce.repository.TransactionRepository;
import com.jumpee.commerce.repository.WalletRepository;
import com.jumpee.commerce.utils.DateAndTime;

@Service
public class OrderService 
{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private DetailsRepository detailsRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private WalletRepository walletRepository;
	
	private int sum = 0;
	
	Order order = new Order();
	Transaction record = new Transaction();
	DateAndTime timestamp = new DateAndTime();
	

	public Order viewOrder(String token) 
	{
		Auth tokenId = authRepository.findByAuthz(token);
		 
		Details details = detailsRepository.findByUser(tokenId.getUser());
		if (walletRepository.findByUser(tokenId.getUser()) == null ) 
		{
			throw new InsufficientBalanceException();
		}
		if (details.getAddress() == null) 
		{
			throw new ShippingNotFound();
		}
		
		Wallet wallet = walletRepository.findByUser(tokenId.getUser());
		List<Cart> list = cartRepository.findByUser(tokenId.getUser());
		
		//list.stream().forEach(c ->System.out.println(c.getAmount()));
	    list.stream().forEach(c ->sum += Integer.valueOf(c.getAmount().intValue()));
		
	    BigDecimal totalAmount = BigDecimal.valueOf(sum);
	    
	    int walletAmount = Integer.valueOf(wallet.getAmount().intValue());
	    int intWallet = Integer.valueOf(totalAmount.intValue());
	    
	    if (walletAmount < intWallet) 
		{
			throw new InsufficientBalanceException();
		}
	    
	   
	    BigDecimal balUpdate = wallet.getAmount().subtract(totalAmount);
	    wallet.setAmount(balUpdate);
	    
	    order.setCart(list);
	    order.setAmount(totalAmount);
	    order.setDetails(details);
	    order.setUser(tokenId.getUser());
	    
	    record.setCategory("Checkout");
		record.setActivity("Order has been processed with total amount of" +balUpdate);
		record.setAtDateAndTime(timestamp.getTimestamp());
		record.setStatus("Success");
		record.setUser(tokenId.getUser());
		
		transactionRepository.save(record);
		return orderRepository.save(order);
	}
}
