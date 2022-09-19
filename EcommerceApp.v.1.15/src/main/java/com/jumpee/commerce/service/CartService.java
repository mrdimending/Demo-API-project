package com.jumpee.commerce.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.exception.StockHandlerException;
import com.jumpee.commerce.model.AddToCart;
import com.jumpee.commerce.model.Auth;
import com.jumpee.commerce.model.Cart;
import com.jumpee.commerce.model.Product;
import com.jumpee.commerce.model.Transaction;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.repository.AuthRepository;
import com.jumpee.commerce.repository.CartRepository;
import com.jumpee.commerce.repository.SingleProductRepository;
import com.jumpee.commerce.repository.TransactionRepository;
import com.jumpee.commerce.utils.DateAndTime;

@Service
public class CartService 
{
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private SingleProductRepository singleproductRepository;
	@Autowired
	private AuthRepository authRepository;

	Cart addToCart = new Cart();
	Transaction record = new Transaction();
	DateAndTime timestamp = new DateAndTime();
	
	public String putToCart(User tokenUserId, Product product, @Valid AddToCart item) 
	{
		if (product.getQty() < item.getQuantity()) 
		{
			throw new StockHandlerException();
		}
		else 
		{
			Product findItem = singleproductRepository.findById(item.getProduct_id());
							
			if(cartRepository.findByProduct(findItem) == null)
			{
				BigDecimal qty = BigDecimal.valueOf(item.getQuantity());
				BigDecimal totalAmount = findItem.getPrice().multiply(qty);
				
				addToCart.setProduct(product);
				addToCart.setUser(tokenUserId);
				addToCart.setQty(item.getQuantity());
				addToCart.setAmount(totalAmount);
				cartRepository.save(addToCart);
				
			}
			else 
			{
				Cart dBitem = cartRepository.findByProduct(findItem);
				int updateQty = dBitem.getQty() + item.getQuantity();
				
				BigDecimal totalQty = BigDecimal.valueOf(updateQty);
				BigDecimal totalAmount = findItem.getPrice().multiply(totalQty);
				
				dBitem.setAmount(totalAmount);
				dBitem.setQty(updateQty);
				cartRepository.save(dBitem);
			}
			
			record.setCategory("Cart");
			record.setActivity("Item "+findItem.getModel()+" ("+item.getQuantity()+" qty) has been added to your cart.");
			record.setAtDateAndTime(timestamp.getTimestamp());
			record.setStatus("Success");
			record.setUser(tokenUserId);
			
			transactionRepository.save(record);
			
			return "Successfully added in the cart";
		}
		
	}

	public List<Cart> getAllProduct(User tokenUserId) 
	{
		Pageable paging = PageRequest.of(0, 3, Sort.by(Direction.DESC, "id"));
		//Pageable paging = PageRequest.of(0, 3);
        Page<Cart> pagedResult = cartRepository.findByUser(tokenUserId, paging);
		return pagedResult.getContent();
		
	}

//	public String addBulkProducts(int product_id, int quantity, String token) 
//	{
//		Auth tokenId = authRepository.findByAuthz(token);
//		Product findItem = singleproductRepository.findById(product_id);
//		
//		if(cartRepository.findByProduct(findItem) == null)
//		{
//			BigDecimal qty = BigDecimal.valueOf(quantity);
//			BigDecimal totalAmount = findItem.getPrice().multiply(qty);
//			
//			addToCart.setProduct(findItem);
//			addToCart.setUser(tokenId.getUser());
//			addToCart.setQty(quantity);
//			addToCart.setAmount(totalAmount);
//			cartRepository.save(addToCart);
//			
//		}
//		else 
//		{
//			Cart dBitem = cartRepository.findByProduct(findItem);
//			int updateQty = dBitem.getQty() + quantity;
//			
//			BigDecimal totalQty = BigDecimal.valueOf(updateQty);
//			BigDecimal totalAmount = findItem.getPrice().multiply(totalQty);
//			
//			dBitem.setAmount(totalAmount);
//			dBitem.setQty(updateQty);
//			cartRepository.save(dBitem);
//		}
//		
//		record.setCategory("Cart");
//		record.setActivity("Item "+findItem.getModel()+" ("+quantity+" qty) has been added to your cart.");
//		record.setAtDateAndTime(timestamp.getTimestamp());
//		record.setStatus("Success");
//		record.setUser(tokenId.getUser());
//		
//		transactionRepository.save(record);
//		
//		return "Item(s) successfully added in the cart";
//		
//	}
	
}
