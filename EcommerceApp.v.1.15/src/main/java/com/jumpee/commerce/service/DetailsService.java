package com.jumpee.commerce.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.model.Details;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.repository.DetailsRepository;

@Service
public class DetailsService 
{
	@Autowired
	private DetailsRepository detailsRepository;

	public Details findById(int id)
	{
		return detailsRepository.findById(id);
	}
	public Details detailsUpdate(User user, @Valid Details details) 
	{
		Details newDetails = new Details();
		newDetails.setUser(user);
		newDetails.setContactPerson(details.getContactPerson());
		newDetails.setContactNum(details.getContactNum());
		newDetails.setAddress(details.getAddress());
		return detailsRepository.save(newDetails);
	}
	public User findUserByAddrId(int addrId) 
	{
		if(detailsRepository.findById(addrId) == null)
		{
			throw new AccessDeniedException();
		}
		Details findUser = detailsRepository.findById(addrId);
		return findUser.getUser();
	}
	public String deteleDetailsById(int addrId) 
	{
		Details findDetails = detailsRepository.findById(addrId);
		detailsRepository.delete(findDetails);
		return "Details successfully deleted";
	}
	public String updateDetailsById(int addrId, Details details) 
	{
		Details findDetails = detailsRepository.findById(addrId);
		findDetails.setAddress(details.getAddress());
		findDetails.setContactNum(details.getContactNum());
		findDetails.setContactPerson(details.getContactPerson());
		detailsRepository.save(findDetails);
		return "Details successfully updated";
	}
}
