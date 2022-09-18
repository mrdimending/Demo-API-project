package com.jumpee.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpee.commerce.model.Product;

public interface SingleProductRepository extends JpaRepository<Product, Integer>
{
	Product findById(int id);
}
