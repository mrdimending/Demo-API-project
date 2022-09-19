package com.jumpee.commerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.model.Details;
import com.jumpee.commerce.model.Order;
import com.jumpee.commerce.service.AuthService;
import com.jumpee.commerce.service.OrderService;
import com.jumpee.commerce.view.View;

@RestController
public class OrderController 
{
	@Autowired
	private OrderService orderService;
	@Autowired
	private AuthService authService;

	@GetMapping("/checkout")
	@JsonView(View.Base.class)
	public ResponseEntity<Order> checkOut(@RequestParam(defaultValue = "guest") String token)
	{
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		Order order = orderService.viewOrder(token);
		
		return new ResponseEntity<Order>(order, new HttpHeaders(), HttpStatus.FOUND); 
	}
//	incomplete
//	@PutMapping("/pre-checkout")
//	@JsonView(View.Base.class)
//	public ResponseEntity<Order> preCheckout(@RequestParam(defaultValue = "guest") String token, 
//												@Valid @RequestBody Details details)
//	{
//		if(!authService.findAuthz(token) || token.equals("guest"))
//		{
//			throw new AccessDeniedException();
//		}
//		Order order = orderService.viewOrder(token);
//		
//		return new ResponseEntity<Order>(order, new HttpHeaders(), HttpStatus.FOUND); 
//	}
}
