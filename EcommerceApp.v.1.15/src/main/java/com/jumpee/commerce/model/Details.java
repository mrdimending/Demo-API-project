package com.jumpee.commerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.view.View;

@Entity
@Table(name = "user_details")
public class Details {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@JsonView(View.Base.class)
	@Size(min = 2, message = "First name must be at least 2 characters")
	private String contactPerson;
	
	@JsonView(View.Base.class)
	@Pattern(regexp = "^(09)\\d{9}$", message = "Invalid mobile number")
	private String contactNum;
	
	@JsonView(View.Base.class)
	@Size(min = 8, message = "Address must be at least 8 characters")
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Details()
	{
		
	}
	public Details(int id, String contactPerson, String contactNum, String address, User user) 
	{
		super();
		this.id = id;
		this.contactPerson = contactPerson;
		this.contactNum = contactNum;
		this.address = address;
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
