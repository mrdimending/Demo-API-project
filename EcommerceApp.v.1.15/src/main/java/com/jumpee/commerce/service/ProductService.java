package com.jumpee.commerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.jumpee.commerce.exception.ProductNotFoundException;
import com.jumpee.commerce.model.Product;
import com.jumpee.commerce.repository.ProductRepository;

@Service
public class ProductService 
{
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProduct(int pageNo, int pageSize, String orderBy, String sortBy)
    {
		Direction direction = Direction.valueOf(orderBy.toUpperCase());
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
        Page<Product> pagedResult = productRepository.findAll(paging);
         
        if(pagedResult.hasContent()) 
        {
            return pagedResult.getContent();
        } 
        else 
        {
            return new ArrayList<Product>();
        }
    }

	public List<Product> getRandomProduct() 
	{
		Random rand = new Random();    
	    int randSize = rand.nextInt(10);
	    
		Pageable paging = PageRequest.of(randSize, 3);
		Page<Product> pagedResult = productRepository.findAll(paging);
		return pagedResult.getContent();
	}

	public List<Product> productSearch(String search) 
	{
		List<Product> brand = productRepository.findByManufacturerContainingIgnoreCase(search);
		List<Product> model = productRepository.findByModelContainingIgnoreCase(search);
		List<Product> cpu = productRepository.findByCpuContainingIgnoreCase(search);
		List<Product> gpu = productRepository.findByGpuContainingIgnoreCase(search);
		
		boolean brandExist = brand.isEmpty();
		boolean modelExist = model.isEmpty();
		boolean cpuExist = cpu.isEmpty();
		boolean gpuExist = gpu.isEmpty();
		
		if(!brandExist)
		{
	        return brand;
		}
		else if(!modelExist)
		{
	        return model;
		}
		else if(!cpuExist)
		{
//			cpu.forEach((p) ->
//			System.out.println(p));
	        return cpu;
		}
		else if(!gpuExist)
		{
	        return gpu;
		}
		else
		{
			throw new ProductNotFoundException();
		}
	}
}
