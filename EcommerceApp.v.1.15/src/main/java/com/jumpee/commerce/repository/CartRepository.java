package com.jumpee.commerce.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jumpee.commerce.model.Cart;
import com.jumpee.commerce.model.Product;
import com.jumpee.commerce.model.User;

public interface CartRepository extends PagingAndSortingRepository<Cart, Integer>
{
	Page<Cart> findByUser(User user, Pageable pageable);
	Cart findById(int id);
	Cart findByProduct(Product dbItem);
	List<Cart> findByUser(User user);
	
}
