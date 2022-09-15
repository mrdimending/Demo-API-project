package com.jumpee.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpee.commerce.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
	Role findById(int id);
}