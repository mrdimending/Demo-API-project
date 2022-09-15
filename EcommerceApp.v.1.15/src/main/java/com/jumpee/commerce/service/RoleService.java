package com.jumpee.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.model.Role;
import com.jumpee.commerce.repository.RoleRepository;

@Service
public class RoleService 
{
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findById(int id) 
	{
		return roleRepository.findById(id);
	}

	public void roleCreated()
	{
		Role role = new Role(-1, "Customer");
		roleRepository.save(role);
	}
}
