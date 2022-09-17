package com.jumpee.commerce.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jumpee.commerce.model.Product;

public interface ProductRepository  extends PagingAndSortingRepository<Product, Integer>
{

	List<Product> findByManufacturerContainingIgnoreCase(String search);
	List<Product> findByModelContainingIgnoreCase(String search);	
	List<Product> findByPrice(BigDecimal search);
	List<Product> findByCpuContainingIgnoreCase(String search);
	List<Product> findByGpuContainingIgnoreCase(String search);
	
	
}
