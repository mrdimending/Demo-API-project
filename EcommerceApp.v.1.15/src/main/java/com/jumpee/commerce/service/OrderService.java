package com.jumpee.commerce.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.model.Auth;
import com.jumpee.commerce.model.Cart;
import com.jumpee.commerce.model.Details;
import com.jumpee.commerce.model.Order;
import com.jumpee.commerce.repository.AuthRepository;
import com.jumpee.commerce.repository.CartRepository;
import com.jumpee.commerce.repository.DetailsRepository;
import com.jumpee.commerce.repository.OrderRepository;

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
	
	private int sum = 0;
	
	Order order = new Order();

	public Order viewOrder(String token) 
	{
		Auth tokenId = authRepository.findByAuthz(token);
		
		Details details = detailsRepository.findByUser(tokenId.getUser());
		List<Cart> list = cartRepository.findByUser(tokenId.getUser());
		
		//list.stream().forEach(c ->System.out.println(c.getAmount()));
	    list.stream().forEach(c ->sum += Integer.valueOf(c.getAmount().intValue()));
		
	    BigDecimal totalAmount = BigDecimal.valueOf(sum);
	    
	    order.setCart(list);
	    order.setAmount(totalAmount);
	    order.setDetails(details);
	    order.setUser(tokenId.getUser());
	    
		return orderRepository.save(order);
	}
}
