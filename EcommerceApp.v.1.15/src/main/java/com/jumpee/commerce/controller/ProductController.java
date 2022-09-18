package com.jumpee.commerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.model.Product;
import com.jumpee.commerce.service.AuthService;
import com.jumpee.commerce.service.ProductService;

@RestController
public class ProductController 
{
	@Autowired
	private ProductService productService;
	@Autowired
	private AuthService authService;
		
	@GetMapping("/view-products")
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam(defaultValue = "0") Integer page, 
                        @RequestParam(defaultValue = "9") Integer size,
                        @RequestParam(defaultValue = "DESC") String orderBy,
                        @RequestParam(defaultValue = "id") String sortBy, 
                        @RequestParam(defaultValue = "guest") String token, 
                        HttpServletRequest request) 
    {
		if(token.equals("guest"))
		{	
			List<Product> list = productService.getRandomProduct();
			
			return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.FOUND); 
		}
		else if(!authService.findAuthz(token))
		{
			throw new AccessDeniedException();
		}
		else 
		{
			List<Product> list = productService.getAllProduct(page, size, orderBy, sortBy);
 
			return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.FOUND); 
		}
    }
	@GetMapping("/search")
    public ResponseEntity<List<Product>> getProduct(@RequestParam String search, @RequestParam(defaultValue = "guest") String token)
    {
		if(!authService.findAuthz(token) || token.equals("guest"))
		{
			throw new AccessDeniedException();
		}
		List<Product> list = productService.productSearch(search);
		
		return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.FOUND); 
    }
}
