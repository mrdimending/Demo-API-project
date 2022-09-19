package com.jumpee.commerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.model.AddToCart;
import com.jumpee.commerce.model.Cart;
import com.jumpee.commerce.model.Product;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.response.Message;
import com.jumpee.commerce.service.AuthService;
import com.jumpee.commerce.service.CartService;
import com.jumpee.commerce.service.ProductService;
import com.jumpee.commerce.view.View;

@RestController
public class CartController 
{
	@Autowired
	private CartService cartService;
	@Autowired
	private AuthService authService;
	@Autowired
	private ProductService productService;
	
	@PostMapping("/cart/add")
	public ResponseEntity<?> addToCart(@RequestParam(defaultValue = "guest") String token, @Valid @RequestBody AddToCart item)
	{
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		User tokenUserId = authService.findUserByToken(token);
		Product product = productService.getItemById(item);
		
		//List<Product> list = productService.getProductById(item.getProduct_id());
		String message = cartService.putToCart(tokenUserId, product, item);
		return ResponseEntity.ok().body(new Message(message));
	}
	
	@GetMapping("/cart/view")
	@JsonView(View.Base.class)
	public ResponseEntity<List<Cart>> viewCart(@RequestParam(defaultValue = "guest") String token)
	{
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		User tokenUserId = authService.findUserByToken(token);
		List<Cart> list = cartService.getAllProduct(tokenUserId);
		 
		return new ResponseEntity<List<Cart>>(list, new HttpHeaders(), HttpStatus.FOUND); 
		
	}
	
//	@GetMapping("/cart/multi")
//	public ResponseEntity<?> update(@RequestParam(defaultValue = "guest") String token, @RequestBody List<AddToCart> item) 
//	{
//		if(!authService.findAuthz(token) || token.equals("guest"))
//		{
//			throw new AccessDeniedException();
//		}
//		item.stream().forEach(c -> cartService.addBulkProducts(c.getProduct_id(), c.getQuantity(), token));
//		return ResponseEntity.ok().body(item);
//	}
}
